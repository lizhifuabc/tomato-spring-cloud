package com.tomato.order.enums;

/**
 * 订单状态枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum OrderStatusEnum {
    /**
     * 处理中
     */
    DEAL(100, "处理中"),
    /**
     * 终态-成功
     */
    SUCCESS(200, "成功"),
    /**
     * 终态-失败
     */
    FAIL_CANCEL(300, "系统自动关闭"),
    /**
     * 终态-失败
     */
    FAIL_CHANNEL(301, "通道获取失败");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态名
     */
    private final String msg;

    /**
     * 构造函数
     * @param code
     * @param msg
     */
    OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 根据code获取 OrderStatusEnum
     * @param code
     * @return
     */
    public static OrderStatusEnum getValue(Integer code) {
        for (OrderStatusEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
