package com.tomato.order.enums;

/**
 * 支付状态枚举
 *
 * @author lizhifu
 * @date 2022/6/24
 */
public enum PayStatusEnum {
    /**
     * 未支付
     */
    NOT_PAY(100, "未支付"),
    /**
     * 用户支付中
     */
    USER_PAYING(101, "用户支付中（付款码支付）"),
    /**
     * 支付成功
     */
    SUCCESS(200, "支付成功"),

    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAY_ERROR(300, "支付失败(其他原因，如银行返回失败)"),

    /**
     * 已撤销（付款码支付）
     */
    REVOKED(301, "已撤销（付款码支付）"),
    /**
     * 已关闭
     */
    CLOSED(302, "已关闭"),

    /**
     * 转入退款
     */
    REFUND(400, "转入退款");
    private final Integer code;

    private final String msg;

    PayStatusEnum(Integer code, String msg) {
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
