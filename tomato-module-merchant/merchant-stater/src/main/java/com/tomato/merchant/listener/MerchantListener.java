package com.tomato.merchant.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 商户创建完成监听器
 * org.springframework.amqp.core.AcknowledgeMode
 * AcknowledgeMode.NONE：自动确认
 * AcknowledgeMode.AUTO：根据情况确认
 * AcknowledgeMode.MANUAL：手动确认
 * @author lizhifu
 * @date 2022/6/20
 */
@Slf4j
@Component
public class MerchantListener {
    @RabbitListener(queues = "merchant.queue",ackMode = "MANUAL")
    public void closeOrder(String merchantNo, Message message, Channel channel) {
        log.info("商户创建完成监听器，发送邮件，商户编号：{}",merchantNo);
        // RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 第三个参数true，表示这个消息会重新进入队列
        // channel.basicNack(deliveryTag, false, true);
    }
}
