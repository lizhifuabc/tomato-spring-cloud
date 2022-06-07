package com.tomato.account.exception;

import com.tomato.data.dto.rep.IResponseCode;

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
    ACCOUNT_BALANCE_NOT_ENOUGH("ACCOUNT101", "账户余额不足"),
    ACCOUNT_HIS_EXIST("ACCOUNT102", "第三方流水号重复"),

    SKILL_ACTIVITY_FAILURE_LIMIT("SKILL103", "库存不足"),
    SKILL_ACTIVITY_FAILURE_USER_LIMIT("SKILL104", "用户达到最大抢购次数"),
    SKILL_COUNT_NOT_ENOUGH("SKILL200", "秒杀数量不足"),
    SKILL_COUNT_LOCK_FAIL("SKILL201", "秒杀失败，请稍后再试")
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
