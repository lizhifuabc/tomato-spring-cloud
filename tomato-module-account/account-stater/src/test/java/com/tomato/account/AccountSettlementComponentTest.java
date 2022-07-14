package com.tomato.account;

import com.tomato.account.component.AccountSettlementComponent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * AccountSettlementComponent
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@SpringBootTest
public class AccountSettlementComponentTest {
    @Resource
    private AccountSettlementComponent accountSettlementComponent;
    @Test
    public void test() {
        accountSettlementComponent.settlement("16577270537276508", LocalDate.now());
    }
}
