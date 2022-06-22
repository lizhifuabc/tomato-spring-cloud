package com.tomato.merchant.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商户费率请求
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class MerchantRateReq extends Req {
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    private String merchantNo;
    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private Integer payType;
}
