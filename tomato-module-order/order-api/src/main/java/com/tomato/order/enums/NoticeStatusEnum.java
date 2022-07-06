package com.tomato.order.enums;

/**
 * 订单来源类型枚举
 *
 * @author lizhifu
 * @date 2022/6/18
 */
public enum NoticeStatusEnum {
    /**
     * 未发送
     */
    DEAL(0, "未发送"),
    /**
     * 已发送
     */
    SUCCESS(1, "已发送");
    private final Integer code;

    private final String msg;

    NoticeStatusEnum(int code, String msg) {
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
