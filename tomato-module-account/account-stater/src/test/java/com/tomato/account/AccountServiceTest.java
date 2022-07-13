package com.tomato.account;

import com.tomato.account.database.dataobject.AccountHisInsertDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * AccountService
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@SpringBootTest
public class AccountServiceTest {
    @Resource
    AccountService accountService;
    @Test
    public void receive() {
        AccountHisInsertDO accountHisInsertDO = new AccountHisInsertDO();
        accountHisInsertDO.setAccountNo("16566619768036789");
        accountHisInsertDO.setAmount(new BigDecimal("100"));
        accountHisInsertDO.setThirdNo("123456789");
        accountHisInsertDO.setAccountHisType("支付测试");
        accountService.receive(accountHisInsertDO);
    }
}
