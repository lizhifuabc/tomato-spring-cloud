package com.tomato.codegen.dbtype;

import com.tomato.codegen.database.dataobject.CodeGenTableFieldDO;
import com.tomato.codegen.database.dataobject.CodeGenTableInfoDO;
import com.tomato.codegen.database.dataobject.CodegenDatasourceDO;
import com.tomato.codegen.dbtype.enums.DbTypeEnums;

import java.util.List;

/**
 * 数据源查询接口
 *
 * @author lizhifu
 * @date 2022/7/30
 */
public interface DbSelectService {
    /**
     * 数据库类型
     */
    DbTypeEnums dbType();

    /**
     * 表名称
     */
    CodeGenTableInfoDO tableInfo(String tableName, CodegenDatasourceDO codegenDatasourceDO) throws Exception;

    /**
     * 表字段信息查询 SQL
     */
    List<CodeGenTableFieldDO> tableFields(String tableName, CodegenDatasourceDO codegenDatasourceDO) throws Exception;
}
