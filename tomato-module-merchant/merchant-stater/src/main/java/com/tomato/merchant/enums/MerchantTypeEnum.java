package com.tomato.merchant.enums;

/**
 * 商户类型
 *
 * @author lizhifu
 * @date 2022/6/22
 */
public enum MerchantTypeEnum {
    /**
     * 交易商户
     */
    TRX(1, "交易商户"),
    /**
     * 分账商户
     */
    SPLIT(2, "分账商户");
    private final Integer code;

    private final String msg;

    MerchantTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
