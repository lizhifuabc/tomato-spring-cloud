package com.tomato.remit.database.dataobject;

import lombok.Data;

/**
 * 完成订单
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class CompleteOrderDO {
    /**
     * 版本号
     */
    private Integer version;
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
