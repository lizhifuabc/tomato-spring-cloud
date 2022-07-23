package com.tomato.remit.enums;

/**
 * 打款状态【0->打款中；1->打款成功；2->打款失败；3->人工处理】
 *
 * @author lizhifu
 * @date 2022/7/23
 */
public enum RemitStatusEnum {
    /**
     * 打款中
     */
    REMIT_ING(0, "打款中"),
    /**
     * 人工处理
     */
    MANUAL_PROCESS(1, "人工处理"),
    /**
     * 打款成功
     */
    REMIT_SUCCESS(2, "打款成功"),
    /**
     * 打款失败
     */
    REMIT_FAIL(3, "打款失败"),
    ;
    private final Integer code;
    private final String msg;
    RemitStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
