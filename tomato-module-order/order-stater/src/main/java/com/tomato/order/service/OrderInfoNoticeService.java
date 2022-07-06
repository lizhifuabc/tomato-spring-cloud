package com.tomato.order.service;

import com.tomato.order.database.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单通知
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Service
@Slf4j
public class OrderInfoNoticeService {
    private final OrderInfoMapper orderInfoMapper;

    public OrderInfoNoticeService(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    public void send(String orderNo) {
        orderInfoMapper.updateNoticeStatus(orderNo);
    }
}
