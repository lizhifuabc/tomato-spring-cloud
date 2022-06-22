package com.tomato.merchant.api;

import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.fallback.MerchantFeignClientFallback;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商户服务
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@FeignClient(value = "tomato-module-merchant", fallback = MerchantFeignClientFallback.class)
public interface MerchantFeignClient {
    @RequestMapping(value = "merchant/rate", method = RequestMethod.POST)
    SingleResponse<MerchantRateRep> getMerchantRate(@RequestBody MerchantRateReq merchantRateReq);
}
