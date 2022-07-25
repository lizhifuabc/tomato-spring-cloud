package com.tomato.remit.api.fallback;

import com.tomato.data.response.ResponseCode;
import com.tomato.data.response.SingleResponse;
import com.tomato.remit.api.RemitOrderFeignClient;
import com.tomato.remit.dto.RemitOrderRep;
import com.tomato.remit.dto.RemitOrderReq;
import lombok.extern.slf4j.Slf4j;

/**
 * 出款服务
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Slf4j
public class RemitOrderFeignClientFallback implements RemitOrderFeignClient {

    @Override
    public SingleResponse<RemitOrderRep> getMerchantRate(RemitOrderReq remitOrderReq) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return SingleResponse.buildFailure(ResponseCode.FAILURE.getCode(),"feign远程调用商户服务异常后的降级方法");
    }
}
