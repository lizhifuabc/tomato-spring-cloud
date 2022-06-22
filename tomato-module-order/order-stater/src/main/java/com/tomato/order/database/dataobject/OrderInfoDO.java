package com.tomato.order.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * order_info
 *
 * @author lizhifu
 * @date 2022/6/22
 */
@Data
public class OrderInfoDO extends BaseDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 收单服务器ip
     */
    private String machineIp;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private BigDecimal requestAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 入账状态
     */
    private String accountStatus;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 通知状态
     */
    private String noticeStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 完成时间
     */
    private LocalDateTime completeDate;

    /**
     * 超时时间
     */
    private LocalDateTime timeoutDate;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 商户订单号
     */
    private String merchantOrderNo;

    /**
     * 手续费
     */
    private BigDecimal merchantFee;

    /**
     * 费率
     */
    private BigDecimal merchantRate;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 页面通知地址
     */
    private String noticeWeb;

    /**
     * 系统通知地址
     */
    private String noticeSys;

    /**
     * 订单类型：标准版或专业版
     */
    private Integer orderType;

    /**
     * 支付号
     */
    private String payNo;
}
