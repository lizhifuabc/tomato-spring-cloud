package com.tomato.merchant.exception;

import com.tomato.data.response.IResponseCode;

/**
 * 返回码
 *
 * @author lizhifu
 * @date 2022/6/19
 */
public enum MerchantResponseCode implements IResponseCode {
    /**
     * 失败
     */
    MERCHANT_FAILURE("MERCHANT100", "邮箱已经存在"),
    ;
    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String message;

    MerchantResponseCode(String code, String message) {
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
