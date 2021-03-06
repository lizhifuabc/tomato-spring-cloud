package com.tomato.rabbitmq.config;

import com.tomato.rabbitmq.service.RabbitReturnsCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 发生异常时的消息返回提醒
 * Exchange -> Queue
 * @author lizhifu
 * @date 2022/3/21
 */
@Slf4j
@Component
public class RabbitTemplateReturnsCallback implements RabbitTemplate.ReturnsCallback {
    private final RabbitTemplate rabbitTemplate;

    private final RabbitReturnsCallbackService rabbitReturnsCallbackService;

    public RabbitTemplateReturnsCallback(RabbitTemplate rabbitTemplate,RabbitReturnsCallbackService rabbitReturnsCallbackService) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitReturnsCallbackService = rabbitReturnsCallbackService;
    }

    @PostConstruct
    public void init(){
        //指定 ConfirmCallback,如果没有抵达队列,以异步发送优先回调returns确认
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(this);
    }
    /**
     * ReturnCallback消息没有正确到达队列时触发回调，如果正确到达队列不执行
     * 消息从交换机Exchange-->队列Queue失败时, 优先回调
     * config : 需要开启rabbitmq发送失败回退
     * @param returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        rabbitReturnsCallbackService.returnedMessage(returned);
    }
}
