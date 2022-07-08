package com.tomato.order.dto;

import com.tomato.data.dto.Req;
import lombok.Data;
import org.checkerframework.common.value.qual.MinLen;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 收单请求
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class OrderCreateReq extends Req {
    /**
     * 订单金额
     */
    @DecimalMin(value = "0.01", message = "订单金额不能小于0.01")
    @NotNull(message = "订单金额不能为空")
    private BigDecimal requestAmount;
    /**
     * 商户订单号
     */
    @NotBlank(message = "商户订单号不能为空")
    private String merchantOrderNo;
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    @Length(min = 4, message = "商户编号长度不能小于4")
    private String merchantNo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 页面通知地址
     */
    private String noticeWeb;

    /**
     * 系统通知地址
     */
    @NotBlank(message = "系统通知地址不能为空")
    private String noticeSys;
    /**
     * 订单类型：标准版或专业版
     */
    @NotNull(message = "订单类型不能为空")
    private int orderType;
    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private Integer payType;
    /** 商户扩展参数 **/
    @Length(max = 128, message = "商户扩展参数长度不能大于128")
    private String extParam;
    /**
     * 签名
     */
    @NotBlank(message = "签名不能为空")
    private String sign;
}
