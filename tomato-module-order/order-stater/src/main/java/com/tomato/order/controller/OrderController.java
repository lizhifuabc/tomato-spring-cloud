package com.tomato.order.controller;

import com.tomato.data.response.ResponseCode;
import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.MerchantFeignClient;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.order.dto.OrderCreateRep;
import com.tomato.order.dto.OrderCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单控制器
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final MerchantFeignClient merchantFeignClient;

    public OrderController(MerchantFeignClient merchantFeignClient) {
        this.merchantFeignClient = merchantFeignClient;
    }

    @PostMapping("/create")
    public SingleResponse<OrderCreateRep> createOrder(@Valid @RequestBody OrderCreateReq orderCreateReq) {
        OrderCreateRep orderCreateRep = new OrderCreateRep();

        MerchantRateReq merchantRateReq = new MerchantRateReq();
        merchantRateReq.setMerchantNo(orderCreateReq.getMerchantNo());
        merchantRateReq.setPayType(orderCreateReq.getPayType());
        SingleResponse<MerchantRateRep> merchantRateRepResponse = merchantFeignClient.getMerchantRate(merchantRateReq);
        log.info("获取商户费率：{}", merchantRateRepResponse);
        if (!merchantRateRepResponse.getCode().equals(ResponseCode.SUCCESS.getCode())) {
            return SingleResponse.buildFailure(merchantRateRepResponse.getCode(), merchantRateRepResponse.getMessage());
        }
        return SingleResponse.of(orderCreateRep);
    }
}
