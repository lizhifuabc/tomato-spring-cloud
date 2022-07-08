package com.tomato.notify;

import com.tomato.notify.pojo.NoticeReceiveReq;
import com.tomato.notify.service.NotifyRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * NotifyRecordService
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@SpringBootTest
public class NotifyRecordServiceTest {
    @Resource
    private NotifyRecordService notifyRecordService;

    @Test
    public void create(){
        NoticeReceiveReq noticeReceiveReq = new NoticeReceiveReq();
        noticeReceiveReq.setNotifyUrl("http://www.baidu.com");
        noticeReceiveReq.setMerchantNo("1");
        noticeReceiveReq.setMerchantOrderNo("test");
        noticeReceiveReq.setOrderNo("test");
        notifyRecordService.create(noticeReceiveReq);
    }
}
