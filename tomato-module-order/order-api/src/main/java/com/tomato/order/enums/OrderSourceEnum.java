package com.tomato.order.enums;

/**
 * 订单来源类型枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum OrderSourceEnum {
    PC(0, "PC订单"),
    APP(1, "APP订单");
    private final Integer code;

    private final String msg;

    OrderSourceEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
