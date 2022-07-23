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
        remitOrderReq.setMerchantName("商户名称");
        remitOrderReq.setRemitRequestNo("R0022000221");
        remitOrderReq.setAccountName("张三");
        remitOrderReq.setAccountNo("62220231234567890123");
        remitOrderReq.setRequestAmount(new BigDecimal("100"));
        remitOrderReq.setBankNo("ICBC");
        remitOrderReq.setBankName("工商银行");
        remitOrderReq.setCity("北京");
        remitOrderReq.setProvince("北京");
        remitOrderReq.setBranchBankName("支行名称");
        remitOrderReq.setNotifyAddress("http://localhost:8080/notify");
        remitOrderComponent.createOrder(remitOrderReq);
    }
}
