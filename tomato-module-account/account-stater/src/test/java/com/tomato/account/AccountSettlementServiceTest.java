package com.tomato.account;

import com.tomato.account.service.AccountSettlementService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * AccountSettlementService
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@SpringBootTest
public class AccountSettlementServiceTest {
    @Resource
    private AccountSettlementService accountSettlementService;
    // 测试
    @Test
    public void dailyCollect() {
        accountSettlementService.dailyCollect("16577270537276508", LocalDate.now().plusDays(1));
    }
}
