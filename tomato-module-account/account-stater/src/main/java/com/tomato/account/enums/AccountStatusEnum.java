package com.tomato.account.enums;

/**
 * 入账状态枚举
 *
 * @author lizhifu
 * @date 2022/6/25
 */
public enum AccountStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 处理中
     */
    DEAL(100, "处理中");
    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    AccountStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
