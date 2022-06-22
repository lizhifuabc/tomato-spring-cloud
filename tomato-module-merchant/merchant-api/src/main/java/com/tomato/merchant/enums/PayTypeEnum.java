package com.tomato.merchant.enums;

/**
 * 支付类型枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum PayTypeEnum {
    WX_SCAN(100, "微信扫码支付");
    PayTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
