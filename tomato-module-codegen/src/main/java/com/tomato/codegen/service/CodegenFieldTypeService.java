package com.tomato.codegen.service;

import com.tomato.codegen.database.dataobject.CodegenFieldTypeDO;
import com.tomato.codegen.dto.CodegenFieldTypeRep;

import java.util.List;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
public interface CodegenFieldTypeService {
    /**
     * 查询所有字段类型
     * @return
     */
    List<CodegenFieldTypeRep> listAll();
    /**
     * 根据字段类型查询字段类型
     * @param columnType
     * @return
     */
    CodegenFieldTypeRep getByColumnType(String columnType);
}
