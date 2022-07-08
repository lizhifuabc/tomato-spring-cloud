package com.tomato.merchant.dto;

import com.tomato.data.dto.Rep;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 商户费率返回
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class MerchantRateRep extends Rep {
    /**
     * 商户费率
     */
    private BigDecimal rate;
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 秘钥
     */
    private String secret;
}
