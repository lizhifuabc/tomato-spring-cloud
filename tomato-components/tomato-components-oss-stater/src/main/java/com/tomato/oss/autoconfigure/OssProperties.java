package com.tomato.oss.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * oss 配置
 *
 * @author lizhifu
 * @date 2022/7/28
 */
@Setter
@Getter
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class OssProperties {
    /**
     * 配置前缀
     */
    public static final String PREFIX = "oss";
}
