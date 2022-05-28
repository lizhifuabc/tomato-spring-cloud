package com.tomato.skill.exception;

import com.tomato.data.exception.AbstractException;

/**
 * 自定义异常
 *
 * @author lizhifu
 * @date 2022/5/28
 */
public class SkillException extends AbstractException {

    public SkillException(String code, String message) {
        super(code, message);
    }

    public SkillException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
