package com.tomato.order.enums;

/**
 * 订单支付类型枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum PayTypeEnum {
    WX_JSAPI(1, "微信JSAPI支付"),
    ALIPAY(2, "支付宝支付"),
    BALANCE(3, "会员余额支付"),
    WX_APP(4, "微信APP支付");

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
