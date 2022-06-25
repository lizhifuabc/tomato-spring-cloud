package com.tomato.order.listener;

import com.rabbitmq.client.Channel;
import com.tomato.order.component.OrderCompleteComponent;
import com.tomato.order.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 延迟队列监听器
 *
 * @author lizhifu
 * @date 2022/6/25
 */
@Slf4j
@Component
public class OrderDelayListener {
    private final OrderCompleteComponent orderCompleteComponent;

    public OrderDelayListener(OrderCompleteComponent orderCompleteComponent) {
        this.orderCompleteComponent = orderCompleteComponent;
    }

    @RabbitListener(queues = "order.delay.queue",ackMode = "MANUAL")
    public void delay(String orderNo, Message message, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("延迟队列：订单 {}",orderNo);
        // TODO 订单关闭 支付记录关闭 支付成功，订单关闭了
        try {
            orderCompleteComponent.completeTimeOut(orderNo);
            // basicAck(tag,multiple)，multiple为true开启批量确认，小于tag值队列中未被消费的消息一次性确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (OrderException e) {
            log.info("延迟队列：订单 {} 失败 {}",orderNo,e.getMessage());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
