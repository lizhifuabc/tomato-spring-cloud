package com.tomato.notify.database;

import com.tomato.notify.database.dataobject.NotifyRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 主键查询
     * @param notifyId
     * @return
     */
    NotifyRecordDO selectByNotifyId(@Param("notifyId") Long notifyId);

    /**
     * 通知完成或者失败后更新通知记录
     * @param notifyId
     * @param notifyStatus
     * @param resResult
     */
    @Update("update notify_record set complete_date = now(),notify_status = #{notifyStatus},res_result = #{resResult},notify_count = notify_count +1 where notify_id = #{notifyId}")
    void completeNotify(@Param("notifyId") Long notifyId, @Param("notifyStatus") Integer notifyStatus, @Param("resResult") String resResult);

    @Select("select count(*) from notify_record where order_no = #{orderNo} limit 1")
    boolean selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 更新通知次数
     * @param notifyId
     * @param notifyStatus
     * @param resResult
     */
    @Update("update notify_record set notify_status = #{notifyStatus},res_result = #{resResult},notify_count = notify_count +1 where notify_id = #{notifyId}")
    void updateNotifyStatus(@Param("notifyId") Long notifyId, @Param("notifyStatus") Integer notifyStatus, @Param("resResult") String resResult);
}
