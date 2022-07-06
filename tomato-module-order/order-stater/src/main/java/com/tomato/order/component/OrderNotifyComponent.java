package com.tomato.order.component;

import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.service.OrderCheckService;
import com.tomato.order.service.OrderInfoNoticeService;
import com.tomato.order.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单通知
 *
 * @author lizhifu
 * @date 2022/7/5
 */
@Component
@Slf4j
public class OrderNotifyComponent {
    private final OrderInfoNoticeService orderInfoNoticeService;
    private final OrderCheckService orderCheckService;
    public OrderNotifyComponent(OrderInfoNoticeService orderInfoNoticeService, OrderCheckService orderCheckService) {
        this.orderInfoNoticeService = orderInfoNoticeService;
        this.orderCheckService = orderCheckService;
    }

    public void orderNotify(String orderNo){
        // TODO
        OrderInfoDO orderInfoDO = orderCheckService.checkOrderStatusFinal(orderNo);
        orderInfoNoticeService.send(orderNo);
    }
}
