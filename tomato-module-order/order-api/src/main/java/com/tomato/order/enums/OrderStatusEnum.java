package com.tomato.order.enums;

/**
 * 订单状态枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum OrderStatusEnum {

    PENDING_PAYMENT(101, "待支付"),
    USER_CANCEL(102, "用户取消"),
    AUTO_CANCEL(103, "系统自动取消"),

    PAYED(201, "已支付"),
    APPLY_REFUND(202, "申请退款"),
    REFUNDED(203, "已退款"),

    DELIVERED(301, "已发货"),

    USER_RECEIVE(401, "用户收货"),
    AUTO_RECEIVE(402, "系统自动收货"),

    FINISHED(901, "已完成");
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
