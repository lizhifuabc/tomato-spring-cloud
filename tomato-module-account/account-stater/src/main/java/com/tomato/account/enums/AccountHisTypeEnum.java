package com.tomato.account.enums;

/**
 * 账户历史类型
 *
 * @author lizhifu
 * @date 2022/7/14
 */
public enum AccountHisTypeEnum {
    /**
     * 结算
     */
    SETTLEMENT("SETTLEMENT", "结算"),
    /**
     * 支付
     */
    PAYMENT("PAYMENT", "支付");
    private final String code;

    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    AccountHisTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
