package com.tomato.account;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDealDO;
import com.tomato.account.database.dataobject.AccountHisUpdateBatchDO;
import com.tomato.account.enums.AccountStatusEnum;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * AccountHisMapper
 *
 * @author lizhifu
 * @date 2022/6/28
 */
@SpringBootTest
public class AccountHisMapperTest {
    @Resource
    private AccountHisMapper accountHisMapper;
    @Resource
    private AccountMapper accountMapper;
    @Test
    public void test(){
        String accountNo = "1L";

        AccountDO accountDO = accountMapper.selectByAccountNo(accountNo);
        // 查询未入账金额和记录
        AccountHisDealDO accountHisDealDO = accountHisMapper.selectDeal(accountNo);
        if(Objects.isNull(accountHisDealDO) || accountHisDealDO.getAccountHisIds().isBlank()
                || accountHisDealDO.getSum().compareTo(BigDecimal.ZERO) < 0){
            return;
        }
        AccountHisUpdateBatchDO accountHisUpdateBatchDO = new AccountHisUpdateBatchDO();
        accountHisUpdateBatchDO.setAccountHisId(Arrays.asList(accountHisDealDO.getAccountHisIds().split(",")));
        accountHisUpdateBatchDO.setAccountStatus(AccountStatusEnum.SUCCESS.getCode());
        accountHisUpdateBatchDO.setVersion(0);
        accountHisUpdateBatchDO.setBeforeBalance(accountDO.getBalance());
        accountHisUpdateBatchDO.setAfterBalance(accountDO.getBalance().add(accountHisDealDO.getSum()));
        // 更新账户历史记录
        int res = accountHisMapper.updateAccountStatusBatch(accountHisUpdateBatchDO);
        if(res != accountHisUpdateBatchDO.getAccountHisId().size()){
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_UPDATE_FAIL);
        }
        // 更新账户余额
        int addRes = accountMapper.add(accountDO.getAccountNo(), accountHisDealDO.getSum(),accountDO.getVersion());
        if(addRes != 1){
            throw new AccountException(AccountResponseCode.ACCOUNT_UPDATE_FAIL);
        }
    }
}
