package com.tomato.order.database;

import com.tomato.order.database.dataobject.PayInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author lizhifu
 * @date 2022/6/24
 */
@Mapper
public interface PayInfoMapper {
    /**
     * 插入支付信息
     * @param payInfoDO
     */
    void insert(PayInfoDO payInfoDO);
}
