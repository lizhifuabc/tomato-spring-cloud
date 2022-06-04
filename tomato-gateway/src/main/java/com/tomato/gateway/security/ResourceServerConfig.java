//package com.tomato.gateway.security;
//
//import com.tomato.gateway.constant.SecurityConstants;
//import com.tomato.gateway.response.GateWayResponseCode;
//import com.tomato.gateway.util.ResponseUtils;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
//import reactor.core.publisher.Mono;
//
//import java.security.interfaces.RSAPublicKey;
//
///**
// * 资源服务器配置
// *
// * @author lizhifu
// * @date 2022/6/4
// */
//@Configuration
//@EnableWebFluxSecurity
//@Slf4j
//public class ResourceServerConfig {
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter())
//                // 本地加载公钥
//                // .publicKey(rsaPublicKey())
//                // 远程获取公钥，默认读取的key是spring.security.oauth2.resourceserver.jwt.jwk-set-uri
//                // .jwkSetUri()
//        ;
//        // ResourceServer 授权异常处理
//        http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
//        http.authorizeExchange()
//                .and()
//                .exceptionHandling()
//                // 处理未授权
//                .accessDeniedHandler(accessDeniedHandler())
//                // 处理未认证
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .and().csrf().disable();
//
//        return http.build();
//    }
//    /**
//     * 自定义未授权响应
//     */
//    @Bean
//    ServerAccessDeniedHandler accessDeniedHandler() {
//        return (exchange, denied) -> {
//            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
//                    .flatMap(response -> ResponseUtils.writeErrorInfo(response,GateWayResponseCode.ACCESS_UNAUTHORIZED));
//            return mono;
//        };
//    }
//    /**
//     * token无效或者已过期自定义响应
//     */
//    @Bean
//    ServerAuthenticationEntryPoint authenticationEntryPoint() {
//        return (exchange, e) -> {
//            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
//                    .flatMap(response -> ResponseUtils.writeErrorInfo(response,GateWayResponseCode.TOKEN_INVALID_OR_EXPIRED));
//            return mono;
//        };
//    }
//    /**
//     * 本地获取JWT验签公钥
//     */
//    @SneakyThrows
//    @Bean
//    public RSAPublicKey rsaPublicKey() {
//        Resource resource = new ClassPathResource("public.key");
//
//        return null;
//    }
//    /**
//     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
//     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
//     * 需要把jwt的Claim中的authorities加入
//     * 方案：重新定义权限管理器，默认转换器JwtGrantedAuthoritiesConverter
//     */
//    @Bean
//    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(SecurityConstants.AUTHORITY_PREFIX);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstants.AUTHORITY_CLAIM_NAME);
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }
//}
