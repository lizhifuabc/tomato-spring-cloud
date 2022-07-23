package com.tomato.remit.channel.handle;

import com.tomato.remit.channel.ChannelService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道
 *
 * @author lizhifu
 * @date 2022/7/23
 */
public class ChannelHandle {
    private static final ConcurrentHashMap<String, ChannelService> CHANNEL_SERVICE_MAP = new ConcurrentHashMap<>();

    /**
     * 添加通道
     * @param channelCode
     * @param value
     */
    public static void add(String channelCode, ChannelService value){
        CHANNEL_SERVICE_MAP.putIfAbsent(channelCode, value);
    }
    /**
     * 获取通道服务
     * @param channelFlag
     * @return
     */
    public static ChannelService getChannelService(String channelFlag) {
        return CHANNEL_SERVICE_MAP.get(channelFlag);
    }

    /**
     * 获取所有通道服务
     * @return
     */
    public static Map getChannelMap() {
        Map<String, String> map = new HashMap<>(16);
        CHANNEL_SERVICE_MAP.forEach((key, value) -> {
            map.put(key, value.channelName());
        });
        return map;
    }
}
