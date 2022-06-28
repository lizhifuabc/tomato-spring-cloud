package com.tomato.order.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 查询订单请求
 *
 * @author lizhifu
 * @date 2022/6/28
 */
@Data
public class OrderQueryReq extends Req {
    /**
     * 商户订单号
     */
    @NotBlank(message = "商户订单号不能为空")
    private String merchantOrderNo;
    /**
     * 商户编号
     */
    @NotBlank(message = "商户编号不能为空")
    private String merchantNo;
}
