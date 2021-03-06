package com.tomato.notify.enums;

/**
 * 订单来源类型枚举
 * 通知状态,0-通知中,1-通知成功,2-通知失败
 * @author lizhifu
 * @date 2022/6/18
 */
public enum NoticeStatusEnum {
    /**
     * 通知中
     */
    DEAL(0, "通知中"),
    /**
     * 通知失败
     */
    FAIL(1, "通知失败"),
    /**
     * 通知成功
     */
    SUCCESS(2, "通知成功");
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
