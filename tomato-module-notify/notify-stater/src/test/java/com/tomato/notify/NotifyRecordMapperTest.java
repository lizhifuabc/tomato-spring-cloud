package com.tomato.notify;

import com.tomato.notify.database.NotifyRecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * NotifyRecordMapper
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@SpringBootTest
public class NotifyRecordMapperTest {
    @Resource
    private NotifyRecordMapper  notifyRecordMapper;

    @Test
    public void test(){
        System.out.println(notifyRecordMapper.selectByNotifyId(1L));
    }
}
