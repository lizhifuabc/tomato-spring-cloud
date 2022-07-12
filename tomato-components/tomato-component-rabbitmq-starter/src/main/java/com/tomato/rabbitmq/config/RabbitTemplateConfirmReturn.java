package com.tomato.rabbitmq.config;

import com.tomato.rabbitmq.service.RabbitConfirmCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 消息发送成功的回调,确认消息到达MQ服务器
 * 需要开启发送确认
 * 生产者 ==>> Exchange 的回调确认
 * publisher-confirm-type 配置为 CORRELATED 或者 SIMPLE 时都会在此处进行回调。
 * @author lizhifu
 * @date 2022/3/21
 */
@Slf4j
public class RabbitTemplateConfirmReturn implements RabbitTemplate.ConfirmCallback {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfirmCallbackService rabbitConfirmCallbackService;
    public RabbitTemplateConfirmReturn(RabbitTemplate rabbitTemplate,RabbitConfirmCallbackService rabbitConfirmCallbackService) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfirmCallbackService = rabbitConfirmCallbackService;
    }

    @PostConstruct
    public void init(){
        log.info("Rabbit 指定 ConfirmCallback");
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
    }
    /**
     * 消息发送到交换机Exchange失败时, 回调
     * @param correlationData 回调的相关数据
     * @param ack ack为真，nack为假
     * @param cause 一个可选的原因，用于nack，如果可用，否则为空
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        rabbitConfirmCallbackService.confirm(correlationData, ack, cause);
    }
}
