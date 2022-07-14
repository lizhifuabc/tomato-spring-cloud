package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitOrderInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 打款订单表
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Mapper
public interface RemitOrderInfoMapper {
    /**
     * 插入打款订单表
     *
     * @param remitOrderInfoDO 打款订单表
     * @return 插入结果
     */
    void insert(RemitOrderInfoDO remitOrderInfoDO);
}
