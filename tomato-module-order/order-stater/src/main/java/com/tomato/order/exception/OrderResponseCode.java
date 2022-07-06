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
     * 订单已经完成
     */
    ORDER_ALREADY_COMPLETE("ORDER201", "订单已经完成"),
    /**
     * 订单已经完成
     */
    ORDER_NOT_COMPLETE("ORDER201", "订单尚未完成"),
    /**
     * 支付已经完成
     */
    PAY_ALREADY_COMPLETE("ORDER203", "支付已经完成"),
    /**
     * 订单更新失败
     */
    ORDER_COMPLETE_FAILURE("ORDER200", "订单更新失败"),
    /**
     * 支付更新失败
     */
    PAY_COMPLETE_FAILURE("ORDER202", "支付更新失败"),
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
