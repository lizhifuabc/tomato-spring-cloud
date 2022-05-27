package com.tomato.module.sys.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static com.tomato.module.sys.biz.config.JwtProperties.PREFIX;

/**
 * JwtProperties
 *
 * @author lizhifu
 * @date 2022/5/18
 */
@ConfigurationProperties(PREFIX)
@PropertySource(value = "classpath:jwt-${spring.profiles.active}.properties")
@Component
public class JwtProperties {
    public static final String PREFIX = "jwt";
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
