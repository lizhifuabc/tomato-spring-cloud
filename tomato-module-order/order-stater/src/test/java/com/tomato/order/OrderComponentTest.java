package com.tomato.order;

import com.tomato.data.response.SingleResponse;
import com.tomato.merchant.api.MerchantFeignClient;
import com.tomato.merchant.dto.MerchantRateRep;
import com.tomato.merchant.dto.MerchantRateReq;
import com.tomato.order.component.OrderComponent;
import com.tomato.order.component.OrderNotifyComponent;
import com.tomato.order.dto.OrderCreateRep;
import com.tomato.order.dto.OrderCreateReq;
import com.tomato.utils.crypto.SignUtil;
import com.tomato.utils.crypto.digest.HmacAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * OrderComponentTest
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@SpringBootTest
public class OrderComponentTest {
    @Resource
    private OrderComponent orderComponent;

    @Resource
    private MerchantFeignClient merchantFeignClient;

    @Test
    public void test(){
        OrderCreateReq orderCreateReq = new OrderCreateReq();
        orderCreateReq.setMerchantOrderNo(System.currentTimeMillis() + "");
        orderCreateReq.setMerchantNo("1657267374932");
        orderCreateReq.setOrderType(100);
        orderCreateReq.setPayType(100);
        orderCreateReq.setExtParam("123");
        orderCreateReq.setRequestAmount(new BigDecimal("1.00"));

        // 获取商户费率 && 校验商户
        MerchantRateReq merchantRateReq = new MerchantRateReq();
        merchantRateReq.setMerchantNo(orderCreateReq.getMerchantNo());
        merchantRateReq.setPayType(orderCreateReq.getPayType());
        SingleResponse<MerchantRateRep> merchantRateRepResponse = merchantFeignClient.getMerchantRate(merchantRateReq);

        Map<String,Object> map = new HashMap<>(16);
        map.put("merchantNo",merchantRateRepResponse.getData().getMerchantNo());
        map.put("merchantOrderNo",orderCreateReq.getMerchantOrderNo());
        map.put("requestAmount",orderCreateReq.getRequestAmount());
        String sign = SignUtil.sign(map, merchantRateRepResponse.getData().getSecret(), HmacAlgorithm.HmacSHA256);
        orderCreateReq.setSign(sign);


        OrderCreateRep orderCreateRep = orderComponent.createOrder(orderCreateReq, merchantRateRepResponse.getData());
        System.out.println(orderCreateRep);
    }
}
