package com.tomato.order.component;

import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.OrderCreateRep;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.order.service.OrderCheckService;
import com.tomato.order.service.OrderService;
import com.tomato.order.service.PayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单组件
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Component
@Slf4j
public class OrderComponent {
    private final OrderCheckService orderCheckService;
    private final OrderService orderService;
    private final PayInfoService payInfoService;
    public OrderComponent(OrderCheckService orderCheckService, OrderService orderService, PayInfoService payInfoService) {
        this.orderCheckService = orderCheckService;
        this.orderService = orderService;
        this.payInfoService = payInfoService;
    }

    public OrderCreateRep createOrder(OrderCreateReq orderCreateReq, MerchantRateRep merchantRateRep) {
        log.info("创建订单：{},{}", orderCreateReq,merchantRateRep);
        // 订单校验 TODO 唯一性流水号校验 redis
        orderCheckService.checkMerchantOrderNo(orderCreateReq.getMerchantNo(), orderCreateReq.getMerchantOrderNo());
        // 订单入库
        OrderInfoDO orderInfoDO = orderService.createOrder(orderCreateReq, merchantRateRep);
        // 发送下游支付请求 TODO
        payInfoService.createPay(orderInfoDO);

        OrderCreateRep orderCreateRep = new OrderCreateRep();
        orderCreateRep.setOrderNo(orderInfoDO.getOrderNo());
        orderCreateRep.setCode("qrCode");
        return orderCreateRep;
        // TODO 超时消息队列处理
    }
}
