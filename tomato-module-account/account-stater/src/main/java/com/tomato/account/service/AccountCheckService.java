package com.tomato.account.service;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.data.enums.CommonStatusEnum;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 账户校验
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Service
public class AccountCheckService {
    private final AccountMapper accountMapper;
    private final AccountHisMapper accountHisMapper;
    public AccountCheckService(AccountMapper accountMapper, AccountHisMapper accountHisMapper) {
        this.accountMapper = accountMapper;
        this.accountHisMapper = accountHisMapper;
    }

    /**
     * 账户校验
     * @param accountReceiveReq
     * @return
     */
    public AccountDO checkReceive(AccountReceiveReq accountReceiveReq) {
        AccountDO accountDO = accountMapper.selectByMerchantNo(accountReceiveReq.getMerchantNo(), CommonStatusEnum.YES.getCode());
        if (accountDO == null) {
            throw new AccountException(AccountResponseCode.ACCOUNT_NOT_EXIST);
        }

        if (accountReceiveReq.getAmount().compareTo(BigDecimal.ZERO) < 0 && accountDO.getBalance().compareTo(accountReceiveReq.getAmount().abs()) < 0) {
            throw new AccountException(AccountResponseCode.ACCOUNT_BALANCE_NOT_ENOUGH);
        }

        AccountHisDO accountHisDO = accountHisMapper.selectByThirdNo(accountDO.getAccountNo(), accountReceiveReq.getThirdNo());
        if (accountHisDO != null) {
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_EXIST);
        }
        return accountDO;
    }
}
