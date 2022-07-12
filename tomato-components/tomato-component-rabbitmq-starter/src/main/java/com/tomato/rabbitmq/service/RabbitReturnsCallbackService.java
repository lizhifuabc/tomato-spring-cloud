package com.tomato.rabbitmq.service;

import org.springframework.amqp.core.ReturnedMessage;

/**
 * 发生异常时的消息返回提醒
 *
 * @author lizhifu
 * @date 2022/7/12
 */
public interface RabbitReturnsCallbackService {
    default void returnedMessage(ReturnedMessage returned){}
}
