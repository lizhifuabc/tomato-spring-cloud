package com.tomato.channel.dto;

import com.tomato.data.dto.Rep;
import com.tomato.data.dto.Req;
import lombok.Data;

/**
 * 发送第三方通道请求返回
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Data
public class ChannelSendRep extends Rep {
    /**
     * 通道返回支付信息
     */
    private String sendUrl;
    /**
     * 支付信息/错误信息
     */
    private String remarksInfo;
}
