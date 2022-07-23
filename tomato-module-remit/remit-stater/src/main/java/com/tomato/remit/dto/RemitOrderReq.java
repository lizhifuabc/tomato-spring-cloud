package com.tomato.remit.dto;

import com.tomato.data.dto.Req;
import lombok.Data;

/**
 * 下单请求
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Data
public class RemitOrderReq extends Req {
    /**
     * 商户编号
     */
    private String merchantNo;
}
