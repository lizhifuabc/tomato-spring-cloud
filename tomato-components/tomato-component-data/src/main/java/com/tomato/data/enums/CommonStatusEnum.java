package com.tomato.data.enums;

/**
 * 0 1 通用枚举类
 *
 * @author lizhifu
 * @date 2022/6/25
 */
public enum CommonStatusEnum {
    /**
     * YES
     */
    YES(0, "YES"),
    /**
     * NO
     */
    NO(1, "NO");
    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CommonStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
