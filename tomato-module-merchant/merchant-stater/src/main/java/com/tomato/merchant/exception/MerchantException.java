package com.tomato.merchant.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class MerchantException extends AbstractException {

    public MerchantException(String code, String message) {
        super(code, message);
    }

    public MerchantException(MerchantResponseCode merchantResponseCode) {
        super(merchantResponseCode.getCode(), merchantResponseCode.getMessage());
    }
}
