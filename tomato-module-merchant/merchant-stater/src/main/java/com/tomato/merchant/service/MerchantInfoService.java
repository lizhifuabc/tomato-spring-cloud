package com.tomato.merchant.service;

import com.tomato.merchant.database.MerchantInfoMapper;
import com.tomato.merchant.database.dataobject.MerchantInfoDO;
import com.tomato.merchant.dto.MerchantCreateReq;
import com.tomato.merchant.exception.MerchantException;
import com.tomato.merchant.exception.MerchantResponseCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商户信息
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@Service
public class MerchantInfoService {
    private final MerchantInfoMapper merchantInfoMapper;
    private final RabbitTemplate rabbitTemplate;

    public MerchantInfoService(MerchantInfoMapper merchantInfoMapper, RabbitTemplate rabbitTemplate) {
        this.merchantInfoMapper = merchantInfoMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 创建商户
     * @param merchantCreateReq
     */
    public void createMerchant(MerchantCreateReq merchantCreateReq) {
        if (merchantInfoMapper.checkMail(merchantCreateReq.getMail())) {
            throw new MerchantException(MerchantResponseCode.MERCHANT_FAILURE);
        }
        MerchantInfoDO merchantInfoDO = new MerchantInfoDO();
        BeanUtils.copyProperties(merchantCreateReq, merchantInfoDO);
        // TODO 商编生成唯一ID
        // 前缀2位:商户类型
        // 时间戳:20220701145449
        merchantInfoDO.setMerchantNo(merchantCreateReq.getMerchantType()+System.currentTimeMillis()+"");
        int i = merchantInfoMapper.insert(merchantInfoDO);
        if (i == 1) {
            // 商户创建成功
            rabbitTemplate.convertAndSend("merchant.exchange","merchant.routing.key", merchantInfoDO.getMerchantNo());
        }
    }
}
