package com.tomato.order.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class OrderException extends AbstractException {

    public OrderException(String code, String message) {
        super(code, message);
    }

    public OrderException(OrderResponseCode orderResponseCode) {
        super(orderResponseCode.getCode(), orderResponseCode.getMessage());
    }
}
