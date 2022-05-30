package com.tomato.skill.exception;

import com.tomato.data.dto.response.IResponseCode;

/**
 * 秒杀活动返回码
 *
 * @author lizhifu
 * @date 2022/5/30
 */
public enum SkillResponseCode implements IResponseCode {
    /**
     * 失败
     */
    SKILL_ACTIVITY_FAILURE("SKILL100", "活动不存在"),
    SKILL_ACTIVITY_FAILURE_END("SKILL101", "活动已经结束"),
    SKILL_ACTIVITY_FAILURE_START("SKILL102", "活动尚未开始"),
    ;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    SkillResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
