package com.tomato.account.component;

import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
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

    public AccountCheckComponent(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public void check(AccountReceiveReq accountReceiveReq) {
        AccountDO accountDO = accountMapper.selectByAccountId(accountReceiveReq.getAccountId());
        if (accountDO == null) {
            throw new AccountException(AccountResponseCode.ACCOUNT_NOT_EXIST);
        }
        if (accountReceiveReq.getAmount().compareTo(BigDecimal.ZERO) < 0 && accountDO.getBalance().compareTo(accountReceiveReq.getAmount()) < 0) {
            throw new AccountException(AccountResponseCode.ACCOUNT_BALANCE_NOT_ENOUGH);
        }
    }
}
