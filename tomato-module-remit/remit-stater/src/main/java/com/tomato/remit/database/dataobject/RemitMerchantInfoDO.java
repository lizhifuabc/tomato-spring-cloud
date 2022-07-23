package com.tomato.remit.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 商户绑定打款渠道表
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class RemitMerchantInfoDO extends BaseDO {
    /**
     * 版本号
     */
    private Integer version;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 绑定状态【0->开；1->关】
     */
    private Integer bindStatus;
    public RemitMerchantInfoDO() {}
}
