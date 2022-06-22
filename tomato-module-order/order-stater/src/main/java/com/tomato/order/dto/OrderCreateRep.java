package com.tomato.order.dto;

import com.tomato.data.dto.Rep;
import lombok.Data;

/**
 * 订单创建返回
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class OrderCreateRep extends Rep {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付二维码
     */
    private String code;
}
