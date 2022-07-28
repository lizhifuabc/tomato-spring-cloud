package com.tomato.oss.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * oss 自动装配类
 *
 * @author lizhifu
 * @date 2022/7/28
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {
}
