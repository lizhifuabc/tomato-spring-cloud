package com.tomato.order.service;

import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.OrderAccountReq;
import com.tomato.order.dto.OrderNoticeReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
        OrderAccountReq orderAccountReq = OrderAccountReq.builder()
                .merchantNo(orderInfoDO.getMerchantNo())
                .thirdNo(orderInfoDO.getOrderNo())
                .amount(orderInfoDO.getRequestAmount().subtract(orderInfoDO.getMerchantFee()))
                .accountHisType("支付")
                .build();
        rabbitTemplate.convertAndSend("order.callback.exchange", null, orderAccountReq,correlationData);
    }
    public void notice(OrderInfoDO orderInfoDO) {
        log.info("通知订单：{}", orderInfoDO);
        // TODO 消息唯一ID生成
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        OrderNoticeReq orderNoticeReq = OrderNoticeReq.builder()
                .orderNo(orderInfoDO.getOrderNo())
                .merchantOrderNo(orderInfoDO.getMerchantOrderNo())
                .merchantNo(orderInfoDO.getMerchantNo())
                // TODO 通知地址参数拼接
                .notifyUrl(orderInfoDO.getNoticeSys())
                .build();
        rabbitTemplate.convertAndSend("order.callback.notice.exchange", "order.callback.notice.routing.key", orderNoticeReq,correlationData);
    }
}
