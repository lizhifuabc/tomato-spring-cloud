package com.tomato.notify.database;

import com.tomato.notify.database.dataobject.NotifyRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商户通知记录表
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Mapper
public interface NotifyRecordMapper {
    /**
     * 插入通知记录
     * @param notifyRecordDO
     * @return
     */
    int insert(NotifyRecordDO notifyRecordDO);
}
