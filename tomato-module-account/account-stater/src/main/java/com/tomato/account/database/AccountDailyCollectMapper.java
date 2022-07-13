package com.tomato.account.database;

import com.tomato.account.database.dataobject.AccountDailyCollectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
