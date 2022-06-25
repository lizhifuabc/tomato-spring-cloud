package com.tomato.order.component;

import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.database.dataobject.PayInfoSelectDO;
import com.tomato.order.dto.CompleteOrderReq;
import com.tomato.order.enums.OrderStatusEnum;
import com.tomato.order.enums.PayStatusEnum;
import com.tomato.order.service.OrderInfoService;
import com.tomato.order.service.PayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单完成
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Component
@Slf4j
public class OrderCompleteComponent {
    private final OrderInfoService orderInfoService;
    private final PayInfoService payInfoService;

    public OrderCompleteComponent(OrderInfoService orderInfoService, PayInfoService payInfoService) {
        this.orderInfoService = orderInfoService;
        this.payInfoService = payInfoService;
    }
    @Transactional(rollbackFor = Exception.class)
    public OrderInfoDO complete(String payNo, OrderStatusEnum orderStatusEnum, PayStatusEnum payStatusEnum,String backInfo) {
        log.info("订单完成：{}", payNo);
        PayInfoSelectDO payInfoSelectDO = payInfoService.completePay(payNo, payStatusEnum,backInfo);

        CompleteOrderReq completeOrderReq = new CompleteOrderReq();
        completeOrderReq.setOrderNo(payInfoSelectDO.getOrderNo());
        completeOrderReq.setPayNo(payNo);
        completeOrderReq.setBackInfo(backInfo);
        completeOrderReq.setOrderStatusEnum(orderStatusEnum);
        OrderInfoDO orderInfoDO = orderInfoService.completeOrder(completeOrderReq);
        return orderInfoDO;
    }
    @Transactional(rollbackFor = Exception.class)
    public void completeTimeOut(String orderNo) {
        log.info("订单超时关闭 orderNo：{}", orderNo);
        // TODO 调用关单或撤销接口API之前，需确认支付状态。
        // TODO 是否需要请求通道进行订单的关闭
        OrderInfoDO orderInfoDO = orderInfoService.completeOrderFast(orderNo, OrderStatusEnum.FAIL_CANCEL);
    }
}
