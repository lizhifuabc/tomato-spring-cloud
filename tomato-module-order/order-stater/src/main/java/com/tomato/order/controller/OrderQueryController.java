package com.tomato.order.controller;

import com.tomato.data.response.SingleResponse;
import com.tomato.order.dto.OrderQueryRep;
import com.tomato.order.dto.OrderQueryReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单查询接口
 * TODO 分库分表下怎么查询
 * @author lizhifu
 * @date 2022/6/28
 */
@RestController
@RequestMapping("/order/query")
public class OrderQueryController {
    @PostMapping("/merchant")
    public SingleResponse<OrderQueryRep> queryByMerchantOrderNo(@RequestBody @Valid OrderQueryReq orderQueryReq){
        OrderQueryRep orderQueryRep = new OrderQueryRep();
        return SingleResponse.of(orderQueryRep);
    }
}
