package com.tomato.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * WebMvcConfig
 * 不要使用 WebMvcConfigurationSupport
 * @author lizhifu
 * @date 2021/12/10
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        log.info("RequestMappingHandlerMapping 配置");
        return new CustomRequestMappingHandlerMapping();
    }
}
