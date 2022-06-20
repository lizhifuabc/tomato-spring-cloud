package com.tomato.merchant.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 商户信息
 *
 * @author lizhifu
 * @date 2022/6/18
 */
@Data
public class MerchantInfoDO extends BaseDO {
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 注册邮箱
     */
    private String mail;
    /**
     * 版本号
     */
    private Integer version;
}
