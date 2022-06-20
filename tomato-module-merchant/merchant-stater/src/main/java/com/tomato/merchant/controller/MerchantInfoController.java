package com.tomato.merchant.controller;

import com.tomato.data.response.Response;
import com.tomato.merchant.dto.MerchantCreateReq;
import com.tomato.merchant.service.MerchantInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商户
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@RestController
@RequestMapping("/merchant")
public class MerchantInfoController {
    private final MerchantInfoService merchantInfoService;

    public MerchantInfoController(MerchantInfoService merchantInfoService) {
        this.merchantInfoService = merchantInfoService;
    }

    @PostMapping("/create")
    public Response createMerchant(@Valid @RequestBody MerchantCreateReq merchantCreateReq) {
        // TODO 发送邮件
        merchantInfoService.createMerchant(merchantCreateReq);
        return Response.buildSuccess();
    }
}
