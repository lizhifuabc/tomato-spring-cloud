package com.tomato.order.database.dataobject;

import lombok.Data;
/**
 * 订单完成
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class OrderCompleteDO {
    private String orderNo;
    private String payNo;
    private Integer version;
    private Integer orderStatus;
}
