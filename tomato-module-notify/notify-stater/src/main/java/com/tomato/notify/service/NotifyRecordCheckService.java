package com.tomato.notify.service;

import com.tomato.notify.database.NotifyRecordMapper;
import com.tomato.notify.database.dataobject.NotifyRecordDO;
import com.tomato.notify.dto.NoticeReceiveReq;
import com.tomato.notify.exception.NotifyException;
import com.tomato.notify.exception.NotifyResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * NotifyRecordService
 *
 * @author lizhifu
 * @date 2022/7/8
 */
@Service
@Slf4j
public class NotifyRecordCheckService {
    private final NotifyRecordMapper notifyRecordMapper;

    public NotifyRecordCheckService(NotifyRecordMapper notifyRecordMapper) {
        this.notifyRecordMapper = notifyRecordMapper;
    }
    public void check(NoticeReceiveReq noticeReceiveReq) {
        // TODO 唯一性流水号校验 redis
        NotifyRecordDO notifyRecordDO = notifyRecordMapper.selectByOrderNo(noticeReceiveReq.getOrderNo());
        if (notifyRecordDO != null) {
            log.warn("订单号已存在：{}",noticeReceiveReq);
            throw new NotifyException(NotifyResponseCode.MERCHANT_ORDER_EXIST);
        }
    }
}
