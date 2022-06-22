package com.tomato.merchant.service;

import com.tomato.merchant.database.MerchantInfoMapper;
import com.tomato.merchant.database.MerchantRateMapper;
import com.tomato.merchant.database.dataobject.MerchantInfoDO;
import com.tomato.merchant.database.dataobject.MerchantRateDO;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.merchant.exception.MerchantException;
import com.tomato.merchant.exception.MerchantResponseCode;
import org.springframework.stereotype.Service;

/**
 * 商户费率信息
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@Service
public class MerchantRateService {
    private final MerchantInfoMapper merchantInfoMapper;
    private final MerchantRateMapper merchantRateMapper;

    public MerchantRateService(MerchantInfoMapper merchantInfoMapper, MerchantRateMapper merchantRateMapper) {
        this.merchantInfoMapper = merchantInfoMapper;
        this.merchantRateMapper = merchantRateMapper;
    }

    /**
     * 商户费率信息
     * @param merchantRateReq
     */
    public MerchantRateRep getMerchantRate(MerchantRateReq merchantRateReq) {
        // 根据商户编号查询商户信息
        MerchantInfoDO merchantInfoDO = merchantInfoMapper.selectByMerchantNo(merchantRateReq.getMerchantNo());
        if (merchantInfoDO == null) {
            throw new MerchantException(MerchantResponseCode.MERCHANT_NOT_EXIST);
        }
        // 根据商户编号和支付类型查询商户费率
        MerchantRateDO merchantRateDO = merchantRateMapper.selectByMerchantNoAndPayType(merchantRateReq.getMerchantNo(), merchantRateReq.getPayType());
        if (merchantRateDO == null) {
            throw new MerchantException(MerchantResponseCode.MERCHANT_RATE_NOT_EXIST);
        }
        MerchantRateRep merchantRateRep = new MerchantRateRep();
        merchantRateRep.setRate(merchantRateDO.getRate());
        merchantRateRep.setMerchantName(merchantInfoDO.getMerchantName());
        merchantRateRep.setMerchantNo(merchantInfoDO.getMerchantNo());
        return merchantRateRep;
    }
}
