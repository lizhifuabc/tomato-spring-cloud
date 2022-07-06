package com.tomato.order.service;

import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.AccountReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 订单mq服务
 *
 * @author lizhifu
 * @date 2022/6/25
 */
@Service
@Slf4j
public class OrderRabbitService {
    private final RabbitTemplate rabbitTemplate;

    public OrderRabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void delayOrder(String orderNo) {
        log.info("延迟订单：{}", orderNo);
    }
    /**
     * 订单支付成功-入账
     * @param orderInfoDO
     */
    public void account(OrderInfoDO orderInfoDO) {
        log.info("入账mq：{}", orderInfoDO);
        // TODO 消息唯一ID生成
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        AccountReq accountReq = AccountReq.builder()
                .merchantNo(orderInfoDO.getMerchantNo())
                .thirdNo(orderInfoDO.getOrderNo())
                .amount(orderInfoDO.getRequestAmount())
                .accountHisType("支付")
                .build();
        rabbitTemplate.convertAndSend("order.callback.exchange", null, accountReq,correlationData);
    }
    public void notice(String orderNo) {
        log.info("通知订单：{}", orderNo);
        rabbitTemplate.convertAndSend("order.notice.exchange", null, orderNo);
    }
}
