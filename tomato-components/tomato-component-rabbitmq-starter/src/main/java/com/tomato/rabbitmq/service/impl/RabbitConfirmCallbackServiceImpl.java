package com.tomato.rabbitmq.service.impl;

import com.tomato.rabbitmq.service.RabbitConfirmCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * RabbitConfirmCallbackService
 *
 * @author lizhifu
 * @date 2022/7/12
 */
@Slf4j
public class RabbitConfirmCallbackServiceImpl implements RabbitConfirmCallbackService {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("确认ack:{} 消息correlationDataID:{}消息发送到交换机成功", ack, correlationData.getId());
            return;
        }
        log.error("确认ack:{} 消息correlationDataID:{}发送到交换机失败 case:{}", ack, correlationData.getId(), cause);
    }
}
