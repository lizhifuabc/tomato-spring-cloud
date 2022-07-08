package com.tomato.notify.service;

import com.tomato.notify.database.NotifyRecordMapper;
import com.tomato.notify.database.dataobject.NotifyRecordDO;
import com.tomato.notify.enums.NoticeStatusEnum;
import com.tomato.notify.enums.SendResBackEnum;
import com.tomato.notify.dto.NoticeReceiveReq;
import com.tomato.utils.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商户通知记录表
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Service
@Slf4j
public class NotifyRecordService {
    private final NotifyRecordMapper notifyRecordMapper;
    private final Snowflake snowflake;
    public NotifyRecordService(NotifyRecordMapper notifyRecordMapper, Snowflake snowflake) {
        this.notifyRecordMapper = notifyRecordMapper;
        this.snowflake = snowflake;
    }
    public NotifyRecordDO create(NoticeReceiveReq noticeReceiveReq) {
        NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
        notifyRecordDO.setNotifyId(snowflake.nextId());
        BeanUtils.copyProperties(noticeReceiveReq, notifyRecordDO);
        notifyRecordMapper.insert(notifyRecordDO);
        return notifyRecordDO;
    }
    public NotifyRecordDO sendNotify(Long notifyId) {
        NotifyRecordDO notifyRecordDO = notifyRecordMapper.selectByNotifyId(notifyId);
        log.info("发送商户通知MQ, msg={}", notifyRecordDO);
        if(notifyRecordDO.getNotifyStatus() > NoticeStatusEnum.DEAL.getCode()) {
            log.info("查询通知记录不存在或状态不是通知中:{}",notifyRecordDO.getNotifyId());
            return notifyRecordDO;
        }
        if( notifyRecordDO.getNotifyCount() >= notifyRecordDO.getNotifyCountLimit() ){
            log.info("已达到最大发送次数:{}",notifyRecordDO.getNotifyId());
            return notifyRecordDO;
        }
        String res = "";
        try {
            // TODO: 2020/7/6 发送通知
            Long sign = System.currentTimeMillis();
            if(sign % 2 == 0){
                res = "success";
            }else {
                throw new RuntimeException("发送失败");
            }
        } catch (Exception e) {
            log.error("http error", e);
            res = "连接异常:【" + e.getMessage() + "】";
        }

        //通知成功
        if(SendResBackEnum.SUCCESS.name().equalsIgnoreCase(res)){
            notifyRecordMapper.updateNotifyStatus(notifyRecordDO.getNotifyId(), NoticeStatusEnum.SUCCESS.getCode(), res);
            return notifyRecordDO;
        }
        // 通知失败
        // 通知次数 >= 最大通知次数时， 更新响应结果为失败， 不在继续延迟发送消息
        if((notifyRecordDO.getNotifyCount() +1) >= notifyRecordDO.getNotifyCountLimit() ){
            notifyRecordMapper.updateNotifyStatus(notifyRecordDO.getNotifyId(), NoticeStatusEnum.FAIL.getCode(), res);
            return notifyRecordDO;
        }

        // 继续发送MQ 延迟发送
        // mchNotifyRecordService.updateNotifyStatus(notifyRecordDO.getNotifyId(), NoticeStatusEnum.DEAL.getCode(), res);
        // 通知延时次数
        //        1   2  3  4   5   6
        //        0  30 60 90 120 150
        // mqSender.send(PayOrderMchNotifyMQ.build(notifyId), currentCount * 30);

        return notifyRecordDO;
    }
}
