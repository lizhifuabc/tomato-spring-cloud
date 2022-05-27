package com.tomato.swagger2.spring.boot.autoconfigure;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.net.HttpHeaders;
import com.tomato.swagger2.spring.boot.autoconfigure.properties.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ExampleBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


/**
 * swagger2 配置
 *
 * @author lizhifu
 * @date 2022/5/16
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "tomato.swagger", value = "enable", matchIfMissing = true)
public class Swagger2AutoConfiguration {
    @Bean
    public Docket createRestApi(SwaggerProperties SwaggerProperties) {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该 API 的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo(SwaggerProperties))
                // 设置扫描指定 package 包下的
                .select()
                .apis(basePackage(SwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .globalRequestParameters(globalRequestParameters())
                .securityContexts(securityContexts());
    }
    /**
     * API 摘要信息
     */
    private static ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(properties.getAuthor(), null, null))
                .version(properties.getVersion())
                .build();
    }
    /**
     * 安全模式，这里配置通过请求头 Authorization 传递 token 参数
     */
    private static List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header"));
    }
    /**
     * 安全上下文
     *
     * @see #securitySchemes()
     * @see #authorizationScopes()
     */
    private static List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
    }

    private static List<SecurityReference> securityReferences() {
        return Collections.singletonList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes()));
    }

    private static AuthorizationScope[] authorizationScopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")};
    }

    private static List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder tenantParameter = new RequestParameterBuilder().name("tenant-id").description("租户编号")
                .in(ParameterType.HEADER).example(new ExampleBuilder().value(1L).build());
        return Collections.singletonList(tenantParameter.build());
    }
}
