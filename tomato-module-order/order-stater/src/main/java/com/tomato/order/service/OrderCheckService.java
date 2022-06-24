package com.tomato.order.service;

import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.exception.OrderException;
import com.tomato.order.exception.OrderResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单相关校验服务
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Service
@Slf4j
public class OrderCheckService {
    private final OrderInfoMapper orderInfoMapper;

    public OrderCheckService(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    /**
     * 校验商户订单号是否存在
     * @param merchantNo
     * @param merchantOrderNo
     */
    public void checkMerchantOrderNo(String merchantNo, String merchantOrderNo) {
        boolean checkMerchantOrderNo = orderInfoMapper.checkMerchantOrderNo(merchantNo, merchantOrderNo);
        if (checkMerchantOrderNo) {
            log.warn("商户:{},订单号已存在：{}",merchantNo,merchantOrderNo);
            throw new OrderException(OrderResponseCode.MERCHANT_ORDER_EXIST);
        }
    }
}
