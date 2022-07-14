package com.tomato.account.service;

import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.enums.AccountStatusEnum;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 直接操作金额
 * 1 增加、减少金额
 * 2 账户历史
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Service
public class AccountBalanceDirectService {
    private final AccountMapper accountMapper;
    private final AccountHisService accountHisService;
    private final AccountCheckService accountCheckService;

    public AccountBalanceDirectService(AccountMapper accountMapper, AccountHisService accountHisService, AccountCheckService accountCheckService) {
        this.accountMapper = accountMapper;
        this.accountHisService = accountHisService;
        this.accountCheckService = accountCheckService;
    }

    /**
     * 金额直接操作
     * @param accountReceiveReq
     */
    @Transactional(rollbackFor = Exception.class)
    public void balanceDirect(AccountReceiveReq accountReceiveReq){
        // 检查账户
        AccountDO accountDO = accountCheckService.checkReceive(accountReceiveReq);
        // 创建账户历史
        AccountHisDO accountHisDO = new AccountHisDO();
        // TODO ID 生成策略
        BeanUtils.copyProperties(accountReceiveReq, accountHisDO);
        accountHisDO.setAccountNo(accountDO.getAccountNo());
        accountHisDO.setAccountStatus(AccountStatusEnum.SUCCESS.getCode());
        accountHisDO.setBeforeBalance(accountDO.getBalance());
        accountHisDO.setAfterBalance(accountDO.getBalance().add(accountReceiveReq.getAmount()));
        accountHisService.create(accountHisDO);
        // 金额操作
        int i;
        if (accountReceiveReq.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            i = accountMapper.add(accountDO.getAccountNo(),accountReceiveReq.getAmount(),accountDO.getVersion());
        } else {
            i = accountMapper.deduct(accountDO.getAccountNo(),accountReceiveReq.getAmount(),accountDO.getVersion());
        }
        if (i != 1) {
            throw new AccountException(AccountResponseCode.ACCOUNT_UPDATE_FAIL);
        }
    }
}