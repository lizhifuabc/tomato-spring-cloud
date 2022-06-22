package com.tomato.merchant.api.fallback;

import com.tomato.data.response.ResponseCode;
import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.MerchantFeignClient;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import lombok.extern.slf4j.Slf4j;

/**
 * 商户服务
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Slf4j
public class MerchantFeignClientFallback implements MerchantFeignClient {

    @Override
    public SingleResponse<MerchantRateRep> getMerchantRate(MerchantRateReq merchantRateReq) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return SingleResponse.buildFailure(ResponseCode.FAILURE.getCode(),"feign远程调用商户服务异常后的降级方法");
    }
}
