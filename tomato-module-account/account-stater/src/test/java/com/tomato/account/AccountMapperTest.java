package com.tomato.account;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.database.dataobject.AccountHisUpdateDO;
import com.tomato.account.dto.AccountCreateReq;
import com.tomato.account.service.AccountCreateService;
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
    private AccountCreateService accountCreateService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountHisMapper accountHisMapper;
    @Test
    public void create() {
        AccountCreateReq accountCreateReq = new AccountCreateReq();
        accountCreateReq.setMerchantNo("1656659426508");
        accountCreateService.create(accountCreateReq);
    }
    @Test
    // 冻结账户
    public void freeze() {
        AccountDO accountDO = accountMapper.selectByAccountNo("16576924079066508");
        BigDecimal amount = new BigDecimal(100);
        System.out.println(accountMapper.freeze(accountDO.getAccountNo(), amount, accountDO.getVersion()));
        accountDO = accountMapper.selectByAccountNo("16576924079066508");
        System.out.println(accountMapper.unfreeze(accountDO.getAccountNo(), amount, accountDO.getVersion()));
    }
    @Test
    public void test1(){
        accountMapper.selectByAccountNo("100000L");
    }
    @Test
    public void test() {
        AccountDO accountDO = accountMapper.selectByAccountNo("1L");

        AccountHisDO insert = new AccountHisDO();
        insert.setAccountNo(accountDO.getAccountNo());
        insert.setAmount(new BigDecimal(-100));
//        insertDO.setAccountHisId(System.currentTimeMillis());
        insert.setThirdNo(UUID.randomUUID().toString());
        insert.setAccountHisType("test");
        accountHisMapper.insert(insert);

        AccountHisDO accountHisDO = accountHisMapper.selectByAccountHisId(12L,accountDO.getAccountNo());

        AccountHisUpdateDO accountHisUpdateDO = new AccountHisUpdateDO();
        accountHisUpdateDO.setAccountHisId(accountHisDO.getAccountHisId());
        accountHisUpdateDO.setAccountStatus(100);
        accountHisUpdateDO.setBeforeBalance(accountDO.getBalance());
        accountHisUpdateDO.setAfterBalance(accountDO.getBalance().add(accountHisDO.getAmount()));
        accountHisUpdateDO.setVersion(accountHisDO.getVersion());
        accountHisMapper.updateAccountStatus(accountHisUpdateDO);

        System.out.println(accountMapper.add(accountDO.getAccountNo(), BigDecimal.TEN,accountDO.getVersion()));
        System.out.println(accountMapper.deduct("1L", new BigDecimal(10),accountDO.getVersion()));
    }
}
