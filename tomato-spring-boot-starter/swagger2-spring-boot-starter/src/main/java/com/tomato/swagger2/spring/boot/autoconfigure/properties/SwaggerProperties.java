package com.tomato.swagger2.spring.boot.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

import static com.tomato.swagger2.spring.boot.autoconfigure.properties.SwaggerProperties.PREFIX;

/**
 * Swagger 属性
 *
 * @author lizhifu
 * @date 2022/5/16
 */
@ConfigurationProperties(PREFIX)
@Data
public class SwaggerProperties {
    public static final String PREFIX = "tomato.swagger";
    /**
     * 标题
     */
    private String title = "在线文档";
    /**
     * 描述
     */
    private String description = "在线文档";
    /**
     * 作者
     */
    private String author = "研发部";
    /**
     * 版本
     */
    private String version = "1.0";
    /**
     * 扫描的包
     */
    @NotEmpty(message = "扫描的 package 不能为空")
    private String basePackage;
}
