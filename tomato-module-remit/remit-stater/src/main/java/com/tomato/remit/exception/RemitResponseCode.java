package com.tomato.remit.exception;

import com.tomato.data.response.IResponseCode;

/**
 * 返回码
 *
 * @author lizhifu
 * @date 2022/6/19
 */
public enum RemitResponseCode implements IResponseCode {
    /**
     * 失败
     */
    REMIT_FAILURE("REMIT100", "通道不存在"),
    MERCHANT_RATE_NOT_EXIST("MERCHANT102", "商户费率信息不存在或者支付方式错误，请联系业务人员"),
    MERCHANT_NOT_EXIST("MERCHANT101", "商户不存在或者状态异常，请联系业务人员");
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
