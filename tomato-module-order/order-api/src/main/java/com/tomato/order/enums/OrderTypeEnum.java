package com.tomato.order.enums;

/**
 * 订单类型
 *
 * @author lizhifu
 * @date 2022/6/22
 */
public enum OrderTypeEnum {
    /**
     * 专业版
     */
    PROFESSIONAL(1, "专业版"),
    /**
     * 标准版
     */
    STANDARD(2, "标准版");
    private final Integer code;

    private final String msg;

    OrderTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
