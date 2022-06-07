package com.tomato.account.component;

import com.tomato.account.database.AccountHisMapper;
import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.database.dataobject.AccountHisDO;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import com.tomato.account.pojo.AccountReceiveReq;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 账户校验
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Component
public class AccountCheckComponent {
    private final AccountMapper accountMapper;
    private final AccountHisMapper accountHisMapper;
    public AccountCheckComponent(AccountMapper accountMapper, AccountHisMapper accountHisMapper) {
        this.accountMapper = accountMapper;
        this.accountHisMapper = accountHisMapper;
    }

    public void checkReceive(AccountReceiveReq accountReceiveReq) {
        AccountDO accountDO = accountMapper.selectByAccountId(accountReceiveReq.getAccountId());
        if (accountDO == null) {
            throw new AccountException(AccountResponseCode.ACCOUNT_NOT_EXIST);
        }

        if (accountReceiveReq.getAmount().compareTo(BigDecimal.ZERO) < 0 && accountDO.getBalance().compareTo(accountReceiveReq.getAmount().abs()) < 0) {
            throw new AccountException(AccountResponseCode.ACCOUNT_BALANCE_NOT_ENOUGH);
        }

        AccountHisDO accountHisDO = accountHisMapper.selectByThirdNo(accountReceiveReq.getAccountId(), accountReceiveReq.getThirdNo());
        if (accountHisDO != null) {
            throw new AccountException(AccountResponseCode.ACCOUNT_HIS_EXIST);
        }
    }
}
