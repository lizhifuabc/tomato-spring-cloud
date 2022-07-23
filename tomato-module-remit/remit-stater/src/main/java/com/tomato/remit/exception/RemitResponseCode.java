package com.tomato.remit.exception;

import com.tomato.data.response.IResponseCode;

/**
 * 返回码
 * 收单成功
 * 收单失败
 * 重复收单
 *
 * @author lizhifu
 * @date 2022/6/19
 */
public enum RemitResponseCode implements IResponseCode {
    /**
     * 失败
     */
    REMIT_FAILURE("REMIT100", "通道不存在"),
    REMIT_EXIST("REMIT101", "重复打款"),
    ;
    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String message;

    RemitResponseCode(String code, String message) {
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
