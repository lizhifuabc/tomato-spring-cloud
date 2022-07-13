package com.tomato.account.exception;

import com.tomato.data.response.IResponseCode;

/**
 * 返回码
 *
 * @author lizhifu
 * @date 2022/5/30
 */
public enum AccountResponseCode implements IResponseCode {
    /**
     * 失败
     */
    ACCOUNT_NOT_EXIST("ACCOUNT100", "账户不存在"),
    ACCOUNT_EXIST("ACCOUNT105", "账户已经存在"),
    ACCOUNT_BALANCE_NOT_ENOUGH("ACCOUNT101", "账户余额不足"),
    ACCOUNT_HIS_EXIST("ACCOUNT102", "第三方流水号重复"),

    ACCOUNT_UPDATE_FAIL("ACCOUNT103", "账户余额更新失败"),

    ACCOUNT_HIS_UPDATE_FAIL("ACCOUNT104", "账户余额更新失败"),

    ACCOUNT_DAILY_COLLECT_FAIL("ACCOUNT105", "日汇总账户待结算金额"),
    ;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    AccountResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
