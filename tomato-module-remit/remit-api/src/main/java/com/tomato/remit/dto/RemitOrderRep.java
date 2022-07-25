package com.tomato.remit.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 下单请求返回
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class RemitOrderRep extends Req {
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 打款请求流水号
     */
    private String remitRequestNo;
    /**
     * 订单号
     */
    private String remitOrderNo;
    /**
     * 打款状态
     */
    private Integer remitStatus;
    /**
     * 异常码
     */
    private String exceptionCode;

    /**
     * 异常信息
     */
    private String exceptionInfo;
}
