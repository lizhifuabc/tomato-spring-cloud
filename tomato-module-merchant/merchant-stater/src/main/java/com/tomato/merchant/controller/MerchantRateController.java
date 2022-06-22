package com.tomato.merchant.controller;

import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.merchant.service.MerchantRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商户费率
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@RestController
@Slf4j
@RequestMapping("/merchant")
public class MerchantRateController {
    private final MerchantRateService merchantRateService;

    public MerchantRateController(MerchantRateService merchantRateService) {
        this.merchantRateService = merchantRateService;
    }

    @PostMapping("/rate")
    public SingleResponse getMerchantRate(@Valid @RequestBody MerchantRateReq merchantRateReq) {
        MerchantRateRep merchantRate = merchantRateService.getMerchantRate(merchantRateReq);
        return SingleResponse.of(merchantRate);
    }
}
