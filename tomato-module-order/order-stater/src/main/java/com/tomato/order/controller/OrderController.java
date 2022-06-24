package com.tomato.order.controller;

import com.tomato.data.response.ResponseCode;
import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.MerchantFeignClient;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.order.dto.OrderCreateRep;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.order.service.OrderService;
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
    private final OrderService orderService;

    public OrderController(MerchantFeignClient merchantFeignClient, OrderService orderService) {
        this.merchantFeignClient = merchantFeignClient;
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public SingleResponse<OrderCreateRep> createOrder(@Valid @RequestBody OrderCreateReq orderCreateReq) {
        OrderCreateRep orderCreateRep = new OrderCreateRep();

        MerchantRateReq merchantRateReq = new MerchantRateReq();
        merchantRateReq.setMerchantNo(orderCreateReq.getMerchantNo());
        merchantRateReq.setPayType(orderCreateReq.getPayType());
        SingleResponse<MerchantRateRep> merchantRateRepResponse = merchantFeignClient.getMerchantRate(merchantRateReq);
        log.info("获取商户费率：{}", merchantRateRepResponse);
        if (!merchantRateRepResponse.isSuccess()) {
            return SingleResponse.buildFailure(merchantRateRepResponse.getCode(), merchantRateRepResponse.getMessage());
        }
        orderService.createOrder(orderCreateReq,merchantRateRepResponse.getData());
        return SingleResponse.of(orderCreateRep);
    }
}
