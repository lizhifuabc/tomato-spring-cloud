package com.tomato.remit.channel;

import com.tomato.remit.channel.handle.ChannelHandle;
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
    public ChannelInit(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        // 初始化通道
        applicationContext.getBeansOfType(ChannelService.class).forEach((key, value) -> {
            log.info("初始化通道：{},通道名称：{}", value.channelCode(), value.channelName());
            ChannelHandle.add(value.channelCode(), value);
        });
    }
}
