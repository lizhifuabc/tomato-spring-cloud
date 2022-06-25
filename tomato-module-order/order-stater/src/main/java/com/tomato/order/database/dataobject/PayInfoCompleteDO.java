package com.tomato.order.database.dataobject;

import lombok.Data;

/**
 * 支付记录完成
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class PayInfoCompleteDO {
    private String payNo;
    private Integer version;
    private Integer payStatus;
    private String backInfo;
}
