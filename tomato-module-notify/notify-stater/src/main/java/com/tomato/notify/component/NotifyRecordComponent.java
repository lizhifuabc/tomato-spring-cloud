package com.tomato.notify.component;

import com.tomato.notify.database.dataobject.NotifyRecordDO;
import com.tomato.notify.dto.NoticeReceiveReq;
import com.tomato.notify.service.NotifyRecordCheckService;
import com.tomato.notify.service.NotifyRecordService;
import org.springframework.stereotype.Component;

/**
 * NotifyRecordComponent
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@Component
public class NotifyRecordComponent {
    private final NotifyRecordCheckService notifyRecordCheckService;
    private final NotifyRecordService notifyRecordService;

    public NotifyRecordComponent(NotifyRecordCheckService notifyRecordCheckService, NotifyRecordService notifyRecordService) {
        this.notifyRecordCheckService = notifyRecordCheckService;
        this.notifyRecordService = notifyRecordService;
    }
    public NotifyRecordDO create(NoticeReceiveReq noticeReceiveReq){
        notifyRecordCheckService.check(noticeReceiveReq);
        return notifyRecordService.create(noticeReceiveReq);
    }

    public void sendNotify(Long notifyId) {
        notifyRecordService.sendNotify(notifyId);
    }
}
