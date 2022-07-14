package com.tomato.account;

import com.tomato.account.component.AccountSettlementComponent;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.data.enums.CommonStatusEnum;
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
    @Resource
    private AccountMapper accountMapper;
    @Test
    public void test() {
        AccountDO accountDO = accountMapper.selectByMerchantNo("1656659426508", CommonStatusEnum.YES.getCode());
        LocalDate collectDate = LocalDate.now().plusDays(1);
        accountSettlementComponent.dailyCollect(accountDO.getAccountNo(), collectDate);
        accountSettlementComponent.settlement(accountDO.getAccountNo(), collectDate);
    }
}
