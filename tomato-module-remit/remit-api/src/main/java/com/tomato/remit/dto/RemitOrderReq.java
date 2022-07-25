package com.tomato.remit.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 下单请求
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class RemitOrderReq extends Req {
    /**
     * 打款请求流水号
     */
    @NotBlank(message = "打款请求流水号不能为空")
    private String remitRequestNo;
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    private String merchantNo;

    /**
     * 商户名称
     */
    @NotBlank(message = "商户名称不能为空")
    private String merchantName;
    /**
     * 银行卡账户名称
     */
    @NotBlank(message = "银行卡账户名称不能为空")
    private String accountName;

    /**
     * 银行卡号
     */
    @NotBlank(message = "银行卡号不能为空")
    private String accountNo;

    /**
     * 银行编码
     */
    @NotBlank(message = "银行编码不能为空")
    private String bankNo;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 分行名称
     */
    private String branchBankName;

    /**
     * 省份编码
     */
    private String province;

    /**
     * 城市编码
     */
    private String city;

    /**
     * 通知地址
     * TODO 对接通知系统，暂时不用
     */
    private String notifyAddress;
    /**
     * 打款金额
     */
    @NotNull(message = "打款金额不能为空")
    private BigDecimal requestAmount;
}
