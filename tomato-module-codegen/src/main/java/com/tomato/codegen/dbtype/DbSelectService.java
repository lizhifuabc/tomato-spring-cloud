package com.tomato.codegen.dbtype;

import com.tomato.codegen.dbtype.enums.DbTypeEnums;

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
     * 表信息查询 SQL
     */
    String tablesSql(String tableName);

    /**
     * 表名称
     */
    String tableName();

    /**
     * 表注释
     */
    String tableComment();

    /**
     * 表字段信息查询 SQL
     */
    String tableFieldsSql();

    /**
     * 字段名称
     */
    String fieldName();


    /**
     * 字段类型
     */
    String fieldType();


    /**
     * 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     */
    String fieldKey();
}
