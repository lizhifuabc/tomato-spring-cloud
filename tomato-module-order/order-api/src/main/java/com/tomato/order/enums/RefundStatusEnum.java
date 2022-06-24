package com.tomato.order.enums;

/**
 * 退款状态枚举
 *
 * @author lizhifu
 * @date 2022/6/24
 */
public enum RefundStatusEnum {
    /**
     * 退款异常
     */
    ABNORMAL(1, "退款异常"),
    /**
     * 退款处理中
     */
    PROCESSING(1, "退款处理中"),
    /**
     * 退款成功
     */
    SUCCESS(1, "专业版"),
    /**
     * 退款关闭
     */
    CLOSED(2, "标准版");
    private final Integer code;

    private final String msg;

    RefundStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
