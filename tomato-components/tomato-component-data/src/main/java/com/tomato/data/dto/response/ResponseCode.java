package com.tomato.data.dto.response;

/**
 * 默认返回码
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public enum ResponseCode implements IResponseCode {
    /**
     * 成功
     */
    SUCCESS("200", "请求成功"),
    /**
     * 失败
     */
    FAILURE("500", "请求失败"),
    ;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
