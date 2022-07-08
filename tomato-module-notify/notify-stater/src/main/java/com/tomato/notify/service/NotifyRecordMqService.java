package com.tomato.notify.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * NotifyRecordMqService
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@Service
public class NotifyRecordMqService {
    private final RabbitTemplate rabbitTemplate;

    public NotifyRecordMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendNotify(Long notifyId,int delay) {
        // 消息发送到延迟交换机上 10分钟
        rabbitTemplate.convertAndSend("order.notify.delay.exchange", "order.notify.delay.routing.key", notifyId, a -> {
            a.getMessageProperties().setDelay(Math.toIntExact(delay * 1000));
            return a;
        });
    }
}
