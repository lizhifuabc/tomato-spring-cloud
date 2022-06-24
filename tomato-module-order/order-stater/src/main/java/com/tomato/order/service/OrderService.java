package com.tomato.order.service;

import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.OrderCreateReq;
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
public class OrderService {
    private final OrderInfoMapper orderInfoMapper;
    public OrderService(OrderInfoMapper orderInfoMapper) {
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
}
