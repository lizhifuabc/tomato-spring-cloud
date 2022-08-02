package com.tomato.codegen.dbtype.impl;

import com.tomato.codegen.dbtype.DbSelectService;
import com.tomato.codegen.dbtype.enums.DbTypeEnums;

/**
 * MYSQl 数据源查询接口
 *
 * @author lizhifu
 * @date 2022/7/30
 */
public class MysqlSelectService implements DbSelectService {

    @Override
    public DbTypeEnums dbType() {
        return DbTypeEnums.MYSQL;
    }

    @Override
    public String tablesSql(String tableName) {
        return "select table_name, table_comment from information_schema.tables " +
                "where table_schema = (select database()) " +
                "and table_name = '" + tableName + "' " +
                "order by table_name asc";
    }

    @Override
    public String tableName() {
        return "table_name";
    }

    @Override
    public String tableComment() {
        return "table_comment";
    }

    @Override
    public String tableFieldsSql() {
        return "select column_name, data_type, column_comment, column_key from information_schema.columns "
                + "where table_name = '%s' and table_schema = (select database()) order by ordinal_position";
    }

    @Override
    public String fieldName() {
        return "column_name";
    }

    @Override
    public String fieldType() {
        return "data_type";
    }

    @Override
    public String fieldComment() {
        return "column_comment";
    }

    @Override
    public String fieldKey() {
        return "column_key";
    }
}
