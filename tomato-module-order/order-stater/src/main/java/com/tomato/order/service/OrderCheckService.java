package com.tomato.order.service;

import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.order.database.OrderInfoMapper;
import com.tomato.order.database.dataobject.OrderInfoDO;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.order.enums.OrderStatusEnum;
import com.tomato.order.exception.OrderException;
import com.tomato.order.exception.OrderResponseCode;
import com.tomato.utils.crypto.SignUtil;
import com.tomato.utils.crypto.digest.HmacAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        // TODO 唯一性流水号校验 redis
        boolean checkMerchantOrderNo = orderInfoMapper.checkMerchantOrderNo(merchantNo, merchantOrderNo);
        if (checkMerchantOrderNo) {
            log.warn("商户:{},订单号已存在：{}",merchantNo,merchantOrderNo);
            throw new OrderException(OrderResponseCode.MERCHANT_ORDER_EXIST);
        }
    }

    /**
     * 校验订单是否已经终态
     * @param orderNo
     * @return
     */
    public OrderInfoDO checkOrderStatusFinal(String orderNo) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectByOrderNo(orderNo);
        if (orderInfoDO.getOrderStatus() > OrderStatusEnum.DEAL.getCode()) {
            return orderInfoDO;
        }
        throw new OrderException(OrderResponseCode.ORDER_NOT_COMPLETE);
    }
    public void checkSign(OrderCreateReq orderCreateReq, MerchantRateRep merchantRateRep){
        Map<String,Object> map = new HashMap<>(16);
        map.put("merchantNo",merchantRateRep.getMerchantNo());
        map.put("merchantOrderNo",orderCreateReq.getMerchantOrderNo());
        map.put("requestAmount",orderCreateReq.getRequestAmount());
        String sign = SignUtil.sign(map, merchantRateRep.getSecret(), HmacAlgorithm.HmacSHA256);
        if(!orderCreateReq.getSign().equals(sign)){
            throw new OrderException(OrderResponseCode.MERCHANT_SIGN_ERROR);
        }
    }
}
