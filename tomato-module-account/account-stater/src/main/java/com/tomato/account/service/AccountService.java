package com.tomato.account.service;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.*;
import com.tomato.account.enums.AccountStatusEnum;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

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
    public void receive (AccountHisInsertDO accountHisInsertDO) {
        if (accountHisMapper.checkThirdNo(accountHisInsertDO.getAccountNo(),accountHisInsertDO.getThirdNo())){
            log.error("账户历史表已存在，不能重复插入:{}",accountHisInsertDO);
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_EXIST);
        }
        accountHisMapper.insert(accountHisInsertDO);
    }
    /**
     * TODO 批量入账
     * 账户余额操作，将数据库操作放在服务中，便于后面重试；
     * 如果没有重试，为了减少事务，可以将查询操作提前
     * @param accountHisId
     */
    @Transactional(rollbackFor = Exception.class)
    public void exe(Long accountHisId) {
        AccountHisDO accountHisDO = accountHisMapper.selectByAccountHisId(accountHisId);
        AccountDO accountDO = accountMapper.selectByAccountNo(accountHisDO.getAccountNo());
        log.info("账户余额操作开始 accountDO:{},accountHisDO:{}", accountDO,accountHisDO);

        AccountHisUpdateDO accountHisUpdateDO = new AccountHisUpdateDO();
        accountHisUpdateDO.setAccountHisId(accountHisDO.getAccountHisId());
        accountHisUpdateDO.setAccountStatus(AccountStatusEnum.SUCCESS.getCode());
        accountHisUpdateDO.setBeforeBalance(accountDO.getBalance());
        accountHisUpdateDO.setAfterBalance(accountDO.getBalance().add(accountHisDO.getAmount()));
        accountHisUpdateDO.setVersion(accountHisDO.getVersion());
        int updateState = accountHisMapper.updateAccountStatus(accountHisUpdateDO);
        log.info("更新账户历史状态 account:{},accountHisId:{},updateState:{}",accountDO.getAccountNo(),accountHisId,updateState);
        if (updateState == 0) {
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_UPDATE_FAIL);
        }

        int accountResult;
        if (accountHisDO.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            accountResult = accountMapper.add(accountDO.getAccountNo(), accountHisDO.getAmount(), accountDO.getVersion());
        } else {
            accountResult = accountMapper.deduct(accountDO.getAccountNo(), accountHisDO.getAmount(), accountDO.getVersion());
        }
        log.info("更新账户余额 account:{},accountHisId:{},accountResult:{}",accountDO.getAccountNo(),accountHisId,accountResult);
        if (accountResult == 0) {
            throw new AccountException(AccountResponseCode.ACCOUNT_UPDATE_FAIL);
        }
    }

    /**
     * 批量入账:针对add
     * @param accountNo
     */
    @Transactional(rollbackFor = Exception.class)
    public void exeBatch(String accountNo) {
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
        // 更新数量判断
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
