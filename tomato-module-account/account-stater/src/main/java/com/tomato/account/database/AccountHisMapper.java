package com.tomato.account.database;

import com.tomato.account.database.dataobject.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 账户历史表
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Mapper
public interface AccountHisMapper {
    /**
     * 查询账户历史表  分库分表，需要增加 accountNo,或者定制 accountHisId 也作为分表字段
     * @param accountHisId
     * @return
     */
    AccountHisDO selectByAccountHisId(@Param("accountHisId") Long accountHisId,@Param("accountNo") String accountNo);

    /**
     * 查询账户历史表
     * @param accountNo
     * @param thirdNo
     * @return
     */
    AccountHisDO selectByThirdNo(@Param("accountNo") String accountNo,@Param("thirdNo") String thirdNo);

    /**
     * 查询账户历史
     * @param accountNo
     * @param thirdNo
     * @return
     */
    @Select("select count(*) from account_his where account_no = #{accountNo} and third_no = #{thirdNo} limit 1")
    boolean checkThirdNo(@Param("accountNo") String accountNo,@Param("thirdNo") String thirdNo);
    /**
     * 新增账户历史表
     *
     * @param accountHisInsertDO 账户历史表
     * @return 新增账户历史表数量
     */
    int insert(AccountHisInsertDO accountHisInsertDO);

    /**
     * 更新账户历史状态  分库分表，需要增加 accountNo,或者定制 accountHisId 也作为分表字段
     * @param accountHisUpdateDO
     * @return
     */
    int updateAccountStatus(AccountHisUpdateDO accountHisUpdateDO);

    /**
     * 批量更新账户历史状态  分库分表，需要增加 accountNo,或者定制 accountHisId 也作为分表字段
     * @param accountHisUpdateBatchDO
     * @return
     */
    int updateAccountStatusBatch(AccountHisUpdateBatchDO accountHisUpdateBatchDO);

    /**
     * 查询未入账的账户历史
     * account_status = 100 and version = 0 and amount >0
     * @param accountNo
     * @return
     */
    AccountHisDealDO selectDeal(@Param("accountNo") String accountNo);

    /**
     * 日汇总账户待结算金额
     * @param accountHisDailyCollectDO
     * @return
     */
    AccountHisDailyCollectRepDO dailyCollect(AccountHisDailyCollectDO accountHisDailyCollectDO);
    /**
     * 更新日汇总账户待结算历史记录
     * @param accountHisDailyCollectDO
     * @return
     */
    int updateDailyCollect(AccountHisDailyCollectDO accountHisDailyCollectDO);
}
