package com.tomato.data.exception;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = -8241404005899079085L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误码
     */
    public String getCode() {
        return code;
    }

    /**
     * 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误信息
     * @param cause   异常
     */
    public AbstractException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
