package com.tomato.gateway.response;

import com.tomato.data.response.IResponseCode;

/**
 * 网关返回码
 *
 * @author lizhifu
 * @date 2022/6/4
 */
public enum GateWayResponseCode implements IResponseCode {
    ACCESS_UNAUTHORIZED("401", "未授权"),
    TOKEN_INVALID_OR_EXPIRED("401", "token无效或者已过期");

    GateWayResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
