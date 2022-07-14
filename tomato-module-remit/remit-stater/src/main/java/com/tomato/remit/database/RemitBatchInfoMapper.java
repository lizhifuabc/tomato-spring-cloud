package com.tomato.remit.database;

import com.tomato.remit.database.dataobject.RemitBatchInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 打款批次表
 *
 * @author lizhifu
 * @date 2022/7/14
 */
@Mapper
public interface RemitBatchInfoMapper {
    /**
     * 插入打款批次表
     *
     * @param remitBatchInfoDO 打款批次表
     * @return 插入结果
     */
    void insert(RemitBatchInfoDO remitBatchInfoDO);
}
