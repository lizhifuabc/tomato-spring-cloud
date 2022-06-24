package com.tomato.channel.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 发送第三方通道请求
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class ChannelSendReq extends Req {
    /**
     * 支付记录号
     */
    @NotBlank(message = "支付记录号不能为空")
    private String payNo;
    /**
     * 请求金额
     */
    @NotNull(message = "请求金额不能为空")
    @DecimalMin(value = "0.01", message = "请求金额不能小于0.01")
    private BigDecimal requestAmount;
    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private Integer payType;
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    private String merchantNo;
}
