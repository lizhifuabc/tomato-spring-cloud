package com.tomato.rabbitmq.service.impl;

import com.tomato.rabbitmq.service.RabbitReturnsCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;

/**
 * 发生异常时的消息返回提醒
 *
 * @author lizhifu
 * @date 2022/7/12
 */
@Slf4j
public class RabbitReturnsCallbackServiceImpl implements RabbitReturnsCallbackService {
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        Integer replyCode = returned.getReplyCode();
        String replyText = returned.getReplyText();
        String exchange = returned.getExchange();
        String routingKey = returned.getRoutingKey();
        Message message = returned.getMessage();
        log.error("correlationDataID:{}发送到队列失败 replyCode:{} replyText:{} exchange:{} routingKey:{}",
                message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"),
                replyCode, replyText, exchange, routingKey);
    }
}
