package com.tomato.remit.api;

import com.tomato.data.response.SingleResponse;
import com.tomato.remit.api.fallback.RemitOrderFeignClientFallback;
import com.tomato.remit.dto.RemitOrderRep;
import com.tomato.remit.dto.RemitOrderReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 出款服务
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@FeignClient(value = "tomato-module-remit", fallback = RemitOrderFeignClientFallback.class)
public interface RemitOrderFeignClient {
    @RequestMapping(value = "/remit/order/create", method = RequestMethod.POST)
    SingleResponse<RemitOrderRep> getMerchantRate(@RequestBody RemitOrderReq remitOrderReq);
}
