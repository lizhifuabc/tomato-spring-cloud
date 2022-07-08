package com.tomato.order.dto;

import com.tomato.data.dto.Req;
import lombok.Builder;
import lombok.Data;

/**
 * 通知请求
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@Data
@Builder
public class OrderNoticeReq extends Req {
    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商户订单号
     */
    private String merchantOrderNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 通知地址
     */
    private String notifyUrl;
}
