package com.tomato.account.database;

import com.tomato.account.database.dataobject.AccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * 账户操作
 *
 * @author lizhifu
 * @date 2022/6/7
 */
@Mapper
public interface AccountMapper {
    /**
     * 查询账户
     *
     * @param accountId
     * @return
     */
    AccountDO selectByAccountId(@Param("accountId") Long accountId);

    /**
     * 查询账户
     *
     * @param merchantNo
     * @return
     */
    @Select("select * from account where merchant_no = #{merchantNo} and status = 0")
    AccountDO selectByMerchantNo(@Param("merchantNo") String merchantNo);

    /**
     * 扣钱
     *
     * @param accountId
     * @param amount
     * @param version
     * @return i
     */
    int deduct(@Param("accountId") Long accountId, @Param("amount") BigDecimal amount,@Param("version") Integer version);

    /**
     * 加钱
     * @param accountId
     * @param amount
     * @param version
     * @return
     */
    int add(@Param("accountId") Long accountId, @Param("amount") BigDecimal amount,@Param("version") Integer version);
}
