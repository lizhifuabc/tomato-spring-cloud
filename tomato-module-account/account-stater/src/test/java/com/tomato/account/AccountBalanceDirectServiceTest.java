package com.tomato.account;

import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.service.AccountBalanceDirectService;
import com.tomato.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * AccountBalanceDirectService
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@SpringBootTest
public class AccountBalanceDirectServiceTest {
    @Resource
    private AccountBalanceDirectService accountBalanceDirectService;
    @Resource
    private AccountMapper accountMapper;
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            AccountReceiveReq accountReceiveReq = new AccountReceiveReq();
            accountReceiveReq.setMerchantNo("1656659426508");
            accountReceiveReq.setAmount(new BigDecimal(RandomUtil.randomInt(500)));
            accountReceiveReq.setThirdNo(RandomUtil.randomString(36));
            accountReceiveReq.setAccountHisType("支付");
            accountBalanceDirectService.balanceDirect(accountReceiveReq);
        }
    }
}
