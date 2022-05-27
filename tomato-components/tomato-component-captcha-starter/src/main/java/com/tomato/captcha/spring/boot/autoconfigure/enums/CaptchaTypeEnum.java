package com.tomato.captcha.spring.boot.autoconfigure.enums;

/**
 * 验证码类型枚举
 *
 * @author lizhifu
 * @date 2022/5/26
 */
public enum CaptchaTypeEnum {
    /**
     * 算数
     */
    ARITHMETIC,
    /**
     * 中文
     */
    CHINESE,
    /**
     * 中文闪图
     */
    CHINESE_GIF,
    /**
     * 闪图
     */
    GIF,

    SPEC
}
