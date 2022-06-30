package com.tomato.order.dto;

import com.tomato.order.enums.OrderStatusEnum;
import lombok.Data;

/**
 * 关闭订单
 *
 * @author lizhifu
 * @date 2022/6/25
 */
@Data
public class CompleteOrderReq {
    private String orderNo;
    private String payNo;
    private String backInfo;
    private OrderStatusEnum orderStatusEnum;
}
