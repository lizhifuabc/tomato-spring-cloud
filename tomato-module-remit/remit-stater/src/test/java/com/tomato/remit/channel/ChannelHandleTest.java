package com.tomato.remit.channel;

import com.tomato.remit.channel.handle.ChannelHandle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ChannelHandle
 *
 * @author lizhifu
 * @date 2022/7/23
 */
@SpringBootTest
public class ChannelHandleTest {
    @Test
    public void test() {
        System.out.println(ChannelHandle.getChannelMap());
    }
}
