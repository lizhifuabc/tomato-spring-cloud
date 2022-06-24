package com.tomato.order.service;

import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.OrderCreateRep;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.order.exception.OrderException;
import com.tomato.order.exception.OrderResponseCode;
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

    public OrderCreateRep createOrder(OrderCreateReq orderCreateReq, MerchantRateRep merchantRateRep) {
        log.info("创建订单：{},{}", orderCreateReq,merchantRateRep);
        // TODO 唯一性流水号校验 redis
        boolean checkMerchantOrderNo = orderInfoMapper.checkMerchantOrderNo(orderCreateReq.getMerchantNo(), orderCreateReq.getMerchantOrderNo());
        if (checkMerchantOrderNo) {
            throw new OrderException(OrderResponseCode.MERCHANT_ORDER_EXIST);
        }
        // 模拟获取微信二维码支付接口 TODO
        // TODO 优先获取二维码，还是创建订单
        String qrCode = "https://qr.alipay.com/bax09900";

        OrderInfoDO orderInfoDO = new OrderInfoDO();
        BeanUtils.copyProperties(orderCreateReq, orderInfoDO);
        BeanUtils.copyProperties(merchantRateRep, orderInfoDO);
        // TODO 订单号生成-分布式id生成策略
        orderInfoDO.setOrderNo(System.currentTimeMillis() + "");
        orderInfoDO.setMerchantRate(merchantRateRep.getRate());
        orderInfoDO.setMachineIp(IpUtils.getHostIp());
        orderInfoDO.setMerchantFee(BigDecimalUtils.multiply(merchantRateRep.getRate(),orderCreateReq.getRequestAmount()));

        // TODO 超时消息队列处理

        orderInfoMapper.insert(orderInfoDO);


        OrderCreateRep orderCreateRep = new OrderCreateRep();
        orderCreateRep.setOrderNo(orderInfoDO.getOrderNo());
        orderCreateRep.setCode(qrCode);
        return orderCreateRep;
    }
}
