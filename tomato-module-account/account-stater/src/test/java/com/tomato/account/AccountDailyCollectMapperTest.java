package com.tomato.account;

import com.tomato.account.database.AccountDailyCollectMapper;
import com.tomato.account.database.dataobject.AccountDailyCollectDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * AccountDailyCollectMapper
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@SpringBootTest
public class AccountDailyCollectMapperTest {
    @Resource
    private AccountDailyCollectMapper accountDailyCollectMapper;
    @Test
    public void test(){
        AccountDailyCollectDO accountDailyCollectDO = new AccountDailyCollectDO();
        accountDailyCollectDO.setAccountNo("111111111111");
        accountDailyCollectDO.setRiskDay(0);
        accountDailyCollectDO.setTotalAmount(new BigDecimal(10));
        accountDailyCollectDO.setTotalCount(0);
        accountDailyCollectDO.setCollectDate(LocalDate.now());
        accountDailyCollectDO.setRemark("test");
        System.out.println(accountDailyCollectMapper.insert(accountDailyCollectDO));
    }
}
