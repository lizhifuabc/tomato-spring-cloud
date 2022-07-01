package com.tomato.account.service;

import com.tomato.account.database.AccountMapper;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.dto.AccountCreateReq;
import com.tomato.account.exception.AccountException;
import com.tomato.account.exception.AccountResponseCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 账户创建
 *
 * @author lizhifu
 * @date 2022/7/1
 */
@Service
public class AccountCreateService {
    private final AccountMapper accountMapper;

    public AccountCreateService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public void create(AccountCreateReq accountCreateReq) {
        Optional.ofNullable(accountMapper.selectByMerchantNoWithOutStatus(accountCreateReq.getMerchantNo()))
                .ifPresent(accountDO -> {
                    throw new AccountException(AccountResponseCode.ACCOUNT_EXIST);
                });
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(accountCreateReq, accountDO);
        // TODO 账户编号生成唯一ID
        accountDO.setAccountNo(System.currentTimeMillis() + accountCreateReq.getMerchantNo().substring(accountCreateReq.getMerchantNo().length()-4));
        accountMapper.insert(accountDO);
    }
}
