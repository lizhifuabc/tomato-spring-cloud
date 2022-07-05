package com.tomato.order.controller;

import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.MerchantFeignClient;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.order.component.OrderComponent;
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
    private final OrderComponent orderComponent;

    public OrderController(MerchantFeignClient merchantFeignClient, OrderComponent orderComponent) {
        this.merchantFeignClient = merchantFeignClient;
        this.orderComponent = orderComponent;
    }

    /**
     * 收单接口
     * 单个表的数据尽量控制在500万条以内，分表的数量为2的n次方，方便扩展
     *
     * TODO 分库分表，二(三)个字段，商编+商户订单号，业务流水号
     * TODO 签名验证
     * 1 商户编号+商户订单号存在一张表中，业务流水号存在另一张表中，此时需要保障在同一个库中，存在事务问题
     * 2 商编+商户订单号，业务流水号,通过算法保障能够落到同一张表中，即单表设计
     * 3 单独维护一张表，保存，商编+商户订单号，业务流水号，表名称，通过这张表来确定分表位置
     * 4 基因法：比如根据商编+商户订单号分表，在生成订单号的时候，把分表解决编码到订单号中去
     * 4 冷数据 + 热数据
     * 5 hash取模分片+范围分片：hash是可以解决数据均匀的问题，range可以解决数据迁移问题
     * @param orderCreateReq
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<OrderCreateRep> createOrder(@Valid @RequestBody OrderCreateReq orderCreateReq) {
        // 获取商户费率 && 校验商户
        MerchantRateReq merchantRateReq = new MerchantRateReq();
        merchantRateReq.setMerchantNo(orderCreateReq.getMerchantNo());
        merchantRateReq.setPayType(orderCreateReq.getPayType());
        SingleResponse<MerchantRateRep> merchantRateRepResponse = merchantFeignClient.getMerchantRate(merchantRateReq);
        log.info("获取商户费率：{}", merchantRateRepResponse);
        if (!merchantRateRepResponse.isSuccess()) {
            return SingleResponse.buildFailure(merchantRateRepResponse.getCode(), merchantRateRepResponse.getMessage());
        }
        OrderCreateRep orderCreateRep = orderComponent.createOrder(orderCreateReq, merchantRateRepResponse.getData());
        return SingleResponse.of(orderCreateRep);
    }
}
