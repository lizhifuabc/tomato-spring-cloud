package com.tomato.account.service;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.database.dataobject.AccountHisInsertDO;
import com.tomato.account.database.dataobject.AccountHisUpdateDO;
import com.tomato.account.enums.AccountStatusEnum;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 账户操作服务
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Service
@Slf4j
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountHisMapper accountHisMapper;

    public AccountService(AccountMapper accountMapper, AccountHisMapper accountHisMapper) {
        this.accountMapper = accountMapper;
        this.accountHisMapper = accountHisMapper;
    }
    public AccountDO selectByMerchantNo(String merchantNo) {
        return accountMapper.selectByMerchantNo(merchantNo);
    }
    public void receive (AccountHisInsertDO accountHisInsertDO) {
        if (accountHisMapper.checkThirdNo(accountHisInsertDO.getAccountId(),accountHisInsertDO.getThirdNo())){
            log.error("账户历史表已存在，不能重复插入:{}",accountHisInsertDO);
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_EXIST);
        }
        accountHisMapper.insert(accountHisInsertDO);
    }
    /**
     * 账户余额操作，将数据库操作放在服务中，便于后面重试；
     * 如果没有重试，为了减少事务，可以将查询操作提前
     * @param accountHisId
     */
    @Transactional(rollbackFor = Exception.class)
    public void exe(Long accountHisId) {
        AccountHisDO accountHisDO = accountHisMapper.selectByAccountHisId(accountHisId);
        AccountDO accountDO = accountMapper.selectByAccountId(accountHisDO.getAccountId());
        log.info("账户余额操作开始 accountDO:{},accountHisDO:{}", accountDO,accountHisDO);

        AccountHisUpdateDO accountHisUpdateDO = new AccountHisUpdateDO();
        accountHisUpdateDO.setAccountHisId(accountHisDO.getAccountHisId());
        accountHisUpdateDO.setAccountStatus(AccountStatusEnum.SUCCESS.getCode());
        accountHisUpdateDO.setBeforeBalance(accountDO.getBalance());
        accountHisUpdateDO.setAfterBalance(accountDO.getBalance().add(accountHisDO.getAmount()));
        accountHisUpdateDO.setVersion(accountHisDO.getVersion());
        int updateState = accountHisMapper.updateAccountStatus(accountHisUpdateDO);
        log.info("更新账户历史状态 account:{},accountHisId:{},updateState:{}",accountDO.getAccountId(),accountHisId,updateState);
        if (updateState == 0) {
            throw new RuntimeException("更新账户历史状态失败");
        }

        int accountResult;
        if (accountHisDO.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            accountResult = accountMapper.add(accountDO.getAccountId(), accountHisDO.getAmount(), accountDO.getVersion());
        } else {
            accountResult = accountMapper.deduct(accountDO.getAccountId(), accountHisDO.getAmount(), accountDO.getVersion());
        }
        log.info("更新账户余额 account:{},accountHisId:{},accountResult:{}",accountDO.getAccountId(),accountHisId,accountResult);
        if (accountResult == 0) {
            throw new RuntimeException("更新账户余额失败");
        }
    }

}
