package com.tomato.notify.database.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商户通知记录表
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Data
public class NotifyRecordDO {
    /**
     * 商户通知记录id
     */
    private Long notifyId;

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

    /**
     * 通知响应结果
     */
    private String resResult;

    /**
     * 通知次数
     */
    private Integer notifyCount;

    /**
     * 最大通知次数， 默认6次
     */
    private Integer notifyCountLimit;

    /**
     * 通知状态，0-通知中，1-通知成功，2-通知失败
     */
    private Integer noticeStatus;

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
}
