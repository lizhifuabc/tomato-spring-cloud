package com.tomato.order.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付信息
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class PayInfoDO extends BaseDO {
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

    /**
     * 支付方式
     */
    private Integer payType;
    /**
     * 完成时间
     */
    private LocalDateTime completeDate;

    /**
     * 下游返回地址
     */
    private String sendUrl;

    /**
     * 通道标识
     */
    private String channelFlag;

    /**
     * 回调信息
     */
    private String channelInfo;
    /**
     * 通道信息
     */
    private String backInfo;

    /**
     * 通道成本费率
     */
    private BigDecimal channelRate;
}
