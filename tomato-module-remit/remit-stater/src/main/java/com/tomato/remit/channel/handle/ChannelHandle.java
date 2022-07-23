package com.tomato.remit.channel.handle;

import com.tomato.remit.channel.ChannelService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道
 *
 * @author lizhifu
 * @date 2022/7/23
 */
public class ChannelHandle {
    private static final ConcurrentHashMap<String, ChannelService> channelServiceMap = new ConcurrentHashMap<>();

    /**
     * 添加通道
     * @param channelCode
     * @param value
     */
    public static void add(String channelCode, ChannelService value){
        channelServiceMap.putIfAbsent(channelCode, value);
    }
    /**
     * 获取通道服务
     * @param channelFlag
     * @return
     */
    public static ChannelService getChannelService(String channelFlag) {
        return channelServiceMap.get(channelFlag);
    }
}
