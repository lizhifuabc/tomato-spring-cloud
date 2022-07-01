package com.tomato.account;

import com.tomato.account.dto.AccountCreateReq;
import com.tomato.account.service.AccountCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * AccountCreateService
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@SpringBootTest
public class AccountCreateServiceTest {
    @Resource
    private AccountCreateService accountCreateService;

    @Test
    public void create() {
        AccountCreateReq accountCreateReq = new AccountCreateReq();
        accountCreateReq.setMerchantNo("1656659426508");
        accountCreateService.create(accountCreateReq);
    }
}
