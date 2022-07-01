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
     * @param accountNo
     * @return
     */
    AccountDO selectByAccountNo(@Param("accountNo") String accountNo);

    /**
     * 查询账户
     *
     * @param merchantNo
     * @return
     */
    @Select("select * from account where merchant_no = #{merchantNo} and status = #{status}")
    AccountDO selectByMerchantNo(@Param("merchantNo") String merchantNo, Integer status);
    /**
     * 查询账户
     *
     * @param merchantNo
     * @return
     */
    @Select("select * from account where merchant_no = #{merchantNo}")
    AccountDO selectByMerchantNoWithOutStatus(@Param("merchantNo") String merchantNo);
    /**
     * 扣钱
     *
     * @param accountNo
     * @param amount
     * @param version
     * @return i
     */
    int deduct(@Param("accountNo") String accountNo, @Param("amount") BigDecimal amount,@Param("version") Integer version);

    /**
     * 加钱
     * @param accountNo
     * @param amount
     * @param version
     * @return
     */
    int add(@Param("accountNo") String accountNo, @Param("amount") BigDecimal amount,@Param("version") Integer version);

    /**
     * 插入
     * @param accountDO
     */
    void insert(AccountDO accountDO);
}
