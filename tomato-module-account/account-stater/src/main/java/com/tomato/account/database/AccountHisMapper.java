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
     * 查询账户历史表
     * @param accountHisId
     * @return
     */
    AccountHisDO selectByAccountHisId(@Param("accountHisId") Long accountHisId);

    /**
     * 查询账户历史表
     * @param accountId
     * @param thirdNo
     * @return
     */
    AccountHisDO selectByThirdNo(@Param("accountId") Long accountId,@Param("thirdNo") String thirdNo);

    /**
     * 查询账户历史
     * @param accountId
     * @param thirdNo
     * @return
     */
    @Select("select exists (select 1 from account_his where account_id = #{accountId} and third_no = #{thirdNo} limit 1)")
    boolean checkThirdNo(@Param("accountId") Long accountId,@Param("thirdNo") String thirdNo);
    /**
     * 新增账户历史表
     *
     * @param accountHisInsertDO 账户历史表
     * @return 新增账户历史表数量
     */
    int insert(AccountHisInsertDO accountHisInsertDO);

    /**
     * 更新账户历史状态
     * @param accountHisUpdateDO
     * @return
     */
    int updateAccountStatus(AccountHisUpdateDO accountHisUpdateDO);

    /**
     * 批量更新账户历史状态
     * @param accountHisUpdateBatchDO
     * @return
     */
    int updateAccountStatusBatch(AccountHisUpdateBatchDO accountHisUpdateBatchDO);

    /**
     * 查询未入账的账户历史
     * account_status = 100 and version = 0 and amount >0
     * @param accountId
     * @return
     */
    AccountHisDealDO selectDeal(@Param("accountId") Long accountId);
}
