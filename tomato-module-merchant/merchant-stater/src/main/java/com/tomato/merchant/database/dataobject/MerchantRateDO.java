package com.tomato.merchant.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商户费率表
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class MerchantRateDO extends BaseDO {
    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 费率
     */
    private BigDecimal rate;

    /**
     * 状态【0->正常；1->关闭】
     */
    private Integer status;

    /**
     * 支付方式
     */
    private Integer payType;
}
