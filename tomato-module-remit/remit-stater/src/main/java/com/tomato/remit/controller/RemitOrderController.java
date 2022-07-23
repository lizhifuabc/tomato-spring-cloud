package com.tomato.remit.controller;

import com.tomato.data.response.SingleResponse;
import com.tomato.remit.dto.RemitOrderRep;
import com.tomato.remit.dto.RemitOrderReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 打款收单
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@RestController
@RequestMapping("/remit/order")
public class RemitOrderController {
    @PostMapping("/create")
    public SingleResponse<RemitOrderRep> createOrder(@Valid @RequestBody RemitOrderReq remitOrderReq) {
        return SingleResponse.of(new RemitOrderRep());
    }
}
