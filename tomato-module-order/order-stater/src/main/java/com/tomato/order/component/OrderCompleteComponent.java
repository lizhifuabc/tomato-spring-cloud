package com.tomato.order.component;

import com.tomato.order.database.dataobject.PayInfoSelectDO;
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
    public void complete(String payNo, OrderStatusEnum orderStatusEnum, PayStatusEnum payStatusEnum) {
        log.info("订单完成：{}", payNo);
        PayInfoSelectDO payInfoSelectDO = payInfoService.completePay(payNo, payStatusEnum);
        orderInfoService.completeOrder(payInfoSelectDO, orderStatusEnum);
    }
}
