package com.tomato.notify.pojo;

import com.tomato.data.dto.Req;
import lombok.Data;

/**
 * 商户通知记录表
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Data
public class NoticeReceiveReq extends Req {
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
