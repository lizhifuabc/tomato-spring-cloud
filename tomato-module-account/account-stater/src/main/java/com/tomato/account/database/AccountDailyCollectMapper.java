package com.tomato.account.database;

import com.tomato.account.database.dataobject.AccountDailyCollectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

/**
 * 每日待结算汇总
 *
 * @author lizhifu
 * @date 2022/7/13
 */
@Mapper
public interface AccountDailyCollectMapper {

    /**
     * 新增每日待结算汇总
     *
     * @param accountDailyCollectDO 每日待结算汇总
     * @return 新增每日待结算汇总数量
     */
    int insert(AccountDailyCollectDO accountDailyCollectDO);

    /**
     * 查询每日待结算汇总
     *
     * @param accountNo
     * @param collectDate
     * @return
     */
    AccountDailyCollectDO selectByAccountNoAndCollectDate(@Param("accountNo") String accountNo, @Param("collectDate") LocalDate collectDate);

    /**
     * 更新每日待结算汇总
     * @param accountNo
     * @param collectDate
     * @param version
     */
    @Update("update account_daily_collect set sett_status = 0,version = version + 1 where account_no = #{accountNo} and collect_date = #{collectDate} and version = #{version}")
    int updateSettStatus(@Param("accountNo") String accountNo,@Param("collectDate") LocalDate collectDate,@Param("version") Integer version);

    /**
     * 更新每日待结算汇总备注
     * @param accountNo
     * @param collectDate
     * @param remark
     * @return
     */
    @Update("update account_daily_collect set remark = #{remark} where account_no = #{accountNo} and collect_date = #{collectDate}")
    int updateRemark(@Param("accountNo") String accountNo,@Param("collectDate") LocalDate collectDate,@Param("remark") String remark);
}
