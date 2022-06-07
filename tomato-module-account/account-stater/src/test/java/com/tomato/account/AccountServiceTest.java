package com.tomato.account;

import com.tomato.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
    public void exe() {
        accountService.exe(1654598080126L);
    }
}
