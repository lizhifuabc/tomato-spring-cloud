package com.tomato.notify;

import com.tomato.notify.component.NotifyRecordComponent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * NotifyRecordComponent
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@SpringBootTest
public class NotifyRecordComponentTest {
    @Resource
    private NotifyRecordComponent notifyRecordComponent;

    @Test
    public void test(){
        notifyRecordComponent.sendNotify(1283551628197926L);
    }
}
