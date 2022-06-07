package com.tomato.account.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 账户系统异常
 *
 * @author lizhifu
 * @date 2022/6/7
 */
public class AccountException extends AbstractException {
    public AccountException(String code, String message) {
        super(code, message);
    }

    public AccountException(AccountResponseCode accountResponseCode) {
        super(accountResponseCode.getCode(), accountResponseCode.getMessage());
    }
}
