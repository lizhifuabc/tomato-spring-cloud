package com.tomato.order.controller;

import com.tomato.order.component.OrderCompleteComponent;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.enums.OrderStatusEnum;
import com.tomato.order.enums.PayStatusEnum;
import com.tomato.order.service.OrderRabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付回调控制器
 *
 * @author lizhifu
 * @date 2022/6/20
 */
@RestController
@RequestMapping("/order/callback")
@Slf4j
public class OrderCallBackController {
    private final OrderRabbitService orderRabbitService;
    private final OrderCompleteComponent orderCompleteComponent;
    public OrderCallBackController(OrderRabbitService orderRabbitService, OrderCompleteComponent orderCompleteComponent) {
        this.orderRabbitService = orderRabbitService;
        this.orderCompleteComponent = orderCompleteComponent;
    }

    @RequestMapping("/wx")
    public void wx(String payNo) {
        log.info("支付回调：微信支付 {}",payNo);
        OrderInfoDO orderInfoDO = orderCompleteComponent.complete(payNo, OrderStatusEnum.SUCCESS, PayStatusEnum.SUCCESS, "支付成功");
        orderRabbitService.account(orderInfoDO);
        orderRabbitService.notice(orderInfoDO);
    }
}
