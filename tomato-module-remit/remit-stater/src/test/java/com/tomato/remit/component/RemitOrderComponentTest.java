package com.tomato.remit.component;

import com.tomato.remit.dto.RemitOrderReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * RemitOrderComponent
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@SpringBootTest
public class RemitOrderComponentTest {
    @Resource
    private RemitOrderComponent remitOrderComponent;

    @Test
    public void test(){
        RemitOrderReq remitOrderReq = new RemitOrderReq();
        // 初始化
        remitOrderReq.setMerchantNo("M000001");
        remitOrderReq.setRemitRequestNo("R000001");
        remitOrderReq.setAccountName("张三");
        remitOrderReq.setAccountNo("62220231234567890123");
        remitOrderReq.setRequestAmount(new BigDecimal("100"));
        remitOrderReq.setBankNo("ICBC");
        remitOrderReq.setBankName("工商银行");
        remitOrderReq.setCity("北京");
        remitOrderReq.setProvince("北京");
        remitOrderComponent.createOrder(remitOrderReq);
    }
}
