package com.tomato.account;

import com.tomato.account.component.AccountCheckComponent;
import com.tomato.account.dto.AccountReceiveReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * AccountCheckComponent
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@SpringBootTest
public class AccountCheckComponentTest {
    @Resource
    private AccountCheckComponent accountCheckComponent;

    @Test
    public void test(){
        AccountReceiveReq accountReceiveReq = new AccountReceiveReq();
        accountReceiveReq.setMerchantNo("123456789");
        accountReceiveReq.setAmount(new BigDecimal("100"));
        accountReceiveReq.setThirdNo("thirdNo");
        accountReceiveReq.setAccountHisType("交易");
        accountCheckComponent.checkReceive(accountReceiveReq);
    }
}
