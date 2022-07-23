package com.tomato.remit.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道初始化
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@Slf4j
@Component
public class ChannelInit {
    private final ApplicationContext applicationContext;
    private ConcurrentHashMap<String,ChannelService> channelServiceMap = new ConcurrentHashMap<>();
    public ChannelInit(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        // 初始化通道
        applicationContext.getBeansOfType(ChannelService.class).forEach((key, value) -> {
            log.info("初始化通道：{},通道名称：{}", value.channelCode(), value.channelName());
            channelServiceMap.putIfAbsent(value.channelCode(), value);
        });
    }

    /**
     * 获取通道服务
     * @param channelFlag
     * @return
     */
    public ChannelService getChannelService(String channelFlag) {
        return channelServiceMap.get(channelFlag);
    }
}
