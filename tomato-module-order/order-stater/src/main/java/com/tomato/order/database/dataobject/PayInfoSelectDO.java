package com.tomato.order.database.dataobject;

import lombok.Data;

/**
 * 查询
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class PayInfoSelectDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付号
     */
    private String payNo;

    /**
     * 支付状态
     */
    private Integer payStatus;
}
