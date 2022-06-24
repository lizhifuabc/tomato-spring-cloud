package com.tomato.order.exception;

import com.tomato.data.response.IResponseCode;

/**
 * 返回码
 *
 * @author lizhifu
 * @date 2022/6/19
 */
public enum OrderResponseCode implements IResponseCode {
    /**
     * 失败
     */
    MERCHANT_ORDER_EXIST("ORDER100", "订单已存在");
    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String message;

    OrderResponseCode(String code, String message) {
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
