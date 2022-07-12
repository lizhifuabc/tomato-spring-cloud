package com.tomato.rabbitmq.config;

import com.tomato.rabbitmq.init.RabbitInitializer;
import com.tomato.rabbitmq.service.RabbitConfirmCallbackService;
import com.tomato.rabbitmq.service.RabbitReturnsCallbackService;
import com.tomato.rabbitmq.service.impl.RabbitConfirmCallbackServiceImpl;
import com.tomato.rabbitmq.service.impl.RabbitReturnsCallbackServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    public RabbitTemplateConfirmReturn rabbitTemplateConfirmReturn(RabbitTemplate rabbitTemplate, RabbitConfirmCallbackService rabbitConfirmCallbackService){
        return new RabbitTemplateConfirmReturn(rabbitTemplate,rabbitConfirmCallbackService);
    }
    @Bean
    @ConditionalOnMissingBean(RabbitConfirmCallbackService.class)
    public RabbitConfirmCallbackService rabbitConfirmCallbackService(){
        return new RabbitConfirmCallbackServiceImpl();
    }
    @Bean
    @ConditionalOnMissingBean(RabbitReturnsCallbackService.class)
    public RabbitReturnsCallbackService rabbitReturnsCallbackService(){
        return new RabbitReturnsCallbackServiceImpl();
    }
    @Bean
    public RabbitTemplateReturnsCallback rabbitTemplateReturnsCallback(RabbitTemplate rabbitTemplate,RabbitReturnsCallbackService rabbitReturnsCallbackService){
        return new RabbitTemplateReturnsCallback(rabbitTemplate,rabbitReturnsCallbackService);
    }
}
