package com.tomato.rabbitmq.config;

import com.tomato.rabbitmq.init.RabbitInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq配置
 *
 * @author lizhifu
 * @date 2022/6/19
 */
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
@Slf4j
public class RabbitConfig {
    /**
     * 消息转换器修改
     * 有了这项配置后, 消息在rabbitMq的管理页面就可以显示看到消息的json数据，否则是序列化的数据
     *
     * @return
     */
    @Bean
    public MessageConverter jackJsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    /**
     * 动态创建队列、交换机初始化器
     */
    @Bean
    public RabbitInitializer rabbitInitializer(AmqpAdmin amqpAdmin, RabbitProperties rabbitProperties) {
        return new RabbitInitializer(amqpAdmin, rabbitProperties);
    }
    @Bean
    public RabbitTemplateConfirmReturn rabbitTemplateConfirmReturn(RabbitTemplate rabbitTemplate){
        return new RabbitTemplateConfirmReturn(rabbitTemplate);
    }
    @Bean
    public RabbitTemplateReturnsCallback rabbitTemplateReturnsCallback(RabbitTemplate rabbitTemplate){
        return new RabbitTemplateReturnsCallback(rabbitTemplate);
    }
}
