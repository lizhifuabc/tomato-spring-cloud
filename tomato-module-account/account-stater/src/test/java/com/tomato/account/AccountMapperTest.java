package com.tomato.account;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.database.dataobject.AccountHisInsertDO;
import com.tomato.account.database.dataobject.AccountHisUpdateDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * AccountMapper
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@SpringBootTest
public class AccountMapperTest {
    @Resource
    AccountMapper accountMapper;
    @Resource
    AccountHisMapper accountHisMapper;
    @Test
    public void test() {
        AccountDO accountDO = accountMapper.selectByAccountId(1L);

        AccountHisInsertDO insertDO = new AccountHisInsertDO();
        insertDO.setAccountId(accountDO.getAccountId());
        insertDO.setAmount(new BigDecimal(-100));
        insertDO.setAccountHisId(System.currentTimeMillis());
        insertDO.setThirdNo(UUID.randomUUID().toString());
        insertDO.setAccountHisType("test");
        accountHisMapper.insert(insertDO);

        AccountHisDO accountHisDO = accountHisMapper.selectByAccountHisId(insertDO.getAccountHisId());

        AccountHisUpdateDO accountHisUpdateDO = new AccountHisUpdateDO();
        accountHisUpdateDO.setAccountHisId(accountHisDO.getAccountHisId());
        accountHisUpdateDO.setState(11);
        accountHisUpdateDO.setBeforeBalance(accountDO.getBalance());
        accountHisUpdateDO.setAfterBalance(accountDO.getBalance().add(accountHisDO.getAmount()));
        accountHisUpdateDO.setVersion(accountHisDO.getVersion());
        accountHisMapper.updateState(accountHisUpdateDO);

        System.out.println(accountMapper.add(accountDO.getAccountId(), BigDecimal.TEN,accountDO.getVersion()));
        System.out.println(accountMapper.deduct(1L, new BigDecimal(10),accountDO.getVersion()));
    }
}
