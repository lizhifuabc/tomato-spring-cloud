package com.tomato.notify.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 系统异常
 *
 * @author lizhifu
 * @date 2022/6/7
 */
public class NotifyException extends AbstractException {
    public NotifyException(String code, String message) {
        super(code, message);
    }

    public NotifyException(NotifyResponseCode notifyResponseCode) {
        super(notifyResponseCode.getCode(), notifyResponseCode.getMessage());
    }
}
