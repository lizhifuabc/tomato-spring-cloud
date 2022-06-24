package com.tomato.order.service;

import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.database.dataobject.OrderCompleteDO;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.database.dataobject.PayInfoSelectDO;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.order.enums.OrderStatusEnum;
import com.tomato.utils.BigDecimalUtils;
import com.tomato.utils.net.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 订单服务
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Service
@Slf4j
public class OrderInfoService {
    private final OrderInfoMapper orderInfoMapper;
    public OrderInfoService(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    public OrderInfoDO createOrder(OrderCreateReq orderCreateReq, MerchantRateRep merchantRateRep) {
        // 订单入库
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        BeanUtils.copyProperties(orderCreateReq, orderInfoDO);
        BeanUtils.copyProperties(merchantRateRep, orderInfoDO);
        // TODO 订单号生成-分布式id生成策略
        orderInfoDO.setOrderNo(System.currentTimeMillis() + "");
        orderInfoDO.setMerchantRate(merchantRateRep.getRate());
        orderInfoDO.setMachineIp(IpUtils.getHostIp());
        orderInfoDO.setMerchantFee(BigDecimalUtils.multiply(merchantRateRep.getRate(),orderCreateReq.getRequestAmount()));

        orderInfoMapper.insert(orderInfoDO);
        return orderInfoDO;
    }
    public OrderInfoDO completeOrder(PayInfoSelectDO payInfoSelectDO, OrderStatusEnum orderStatusEnum) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectByOrderNo(payInfoSelectDO.getOrderNo());
        if (orderInfoDO.getOrderStatus() >= OrderStatusEnum.SUCCESS.getCode()){
            throw new RuntimeException("订单是终态");
        }
        OrderCompleteDO orderCompleteDO = new OrderCompleteDO();
        orderCompleteDO.setOrderNo(payInfoSelectDO.getOrderNo());
        orderCompleteDO.setOrderStatus(orderStatusEnum.getCode());
        orderCompleteDO.setPayNo(payInfoSelectDO.getPayNo());
        orderCompleteDO.setVersion(orderInfoDO.getVersion());
        int res = orderInfoMapper.complete(orderCompleteDO);
        if (res == 0) {
            throw new RuntimeException("订单是终态");
        }
        return orderInfoDO;
    }

    /**
     * 快速完成订单
     * @param orderNo
     * @param orderStatusEnum
     * @return
     */
    public OrderInfoDO completeOrderFast(String orderNo, OrderStatusEnum orderStatusEnum) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectByOrderNo(orderNo);
        if (orderInfoDO.getOrderStatus() >= OrderStatusEnum.SUCCESS.getCode()){
            throw new RuntimeException("订单是终态");
        }
        OrderCompleteDO orderCompleteDO = new OrderCompleteDO();
        orderCompleteDO.setOrderNo(orderNo);
        orderCompleteDO.setOrderStatus(orderStatusEnum.getCode());
        orderCompleteDO.setVersion(orderInfoDO.getVersion());
        int res = orderInfoMapper.complete(orderCompleteDO);
        if (res == 0) {
            throw new RuntimeException("订单是终态");
        }
        return orderInfoDO;
    }
}
