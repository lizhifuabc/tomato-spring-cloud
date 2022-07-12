package com.tomato.rabbitmq.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * Rabbit 消息发送成功的回调,确认消息到达MQ服务器
 *
 * @author lizhifu
 * @date 2022/7/11
 */
public interface RabbitConfirmCallbackService {
    /**
     * 消息发送到交换机Exchange失败时, 回调
     *
     * @param correlationData 回调的相关数据
     * @param ack ack为真，nack为假
     * @param cause 一个可选的原因，用于nack，如果可用，否则为空
     */
     default void confirm(CorrelationData correlationData, boolean ack, String cause){}
}
