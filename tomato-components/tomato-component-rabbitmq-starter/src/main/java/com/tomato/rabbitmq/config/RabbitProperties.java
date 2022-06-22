package com.tomato.rabbitmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * rabbitmq配置
 *
 * @author lizhifu
 * @date 2022/6/19
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitProperties {
    /**
     * rabbitmq配置信息
     */
    private List<RabbitInfo> rabbitInfoList;
}
