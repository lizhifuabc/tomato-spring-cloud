package com.tomato.remit.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class RemitException extends AbstractException {

    public RemitException(String code, String message) {
        super(code, message);
    }

    public RemitException(RemitResponseCode remitResponseCode) {
        super(remitResponseCode.getCode(), remitResponseCode.getMessage());
    }
}
