package com.tomato.notify.service;

import com.tomato.notify.database.NotifyRecordMapper;
import com.tomato.notify.database.dataobject.NotifyRecordDO;
import com.tomato.notify.pojo.NoticeReceiveReq;
import com.tomato.utils.lang.Snowflake;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商户通知记录表
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Service
public class NotifyRecordService {
    private final NotifyRecordMapper notifyRecordMapper;
    private final Snowflake snowflake;
    public NotifyRecordService(NotifyRecordMapper notifyRecordMapper, Snowflake snowflake) {
        this.notifyRecordMapper = notifyRecordMapper;
        this.snowflake = snowflake;
    }
    public void create(NoticeReceiveReq noticeReceiveReq) {
        NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
        notifyRecordDO.setNotifyId(snowflake.nextId());
        BeanUtils.copyProperties(noticeReceiveReq, notifyRecordDO);
        notifyRecordMapper.insert(notifyRecordDO);
    }
}
