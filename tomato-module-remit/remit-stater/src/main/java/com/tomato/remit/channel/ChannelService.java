package com.tomato.remit.channel;

/**
 * 打款接口
 *
 * @author lizhifu
 * @date 2022/7/23
 */
public interface ChannelService {
    /**
     * 通道标识
     * @return
     */
    String channelCode();
    /**
     * 通道名称
     * @return
     */
    String channelName();
}
