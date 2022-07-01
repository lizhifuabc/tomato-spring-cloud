package com.tomato.data.enums;

/**
 * 开关状态枚举
 *
 * @author lizhifu
 * @date 2022/6/25
 */
public enum StatusCommonEnum {
    /**
     * 成功
     */
    OPEN(0, "正常"),
    /**
     * 处理中
     */
    CLOSED(1, "关闭");
    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    StatusCommonEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
