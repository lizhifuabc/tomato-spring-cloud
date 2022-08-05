package com.tomato.codegen.dbtype.impl;

import com.tomato.codegen.database.dataobject.CodeGenTableFieldDO;
import com.tomato.codegen.database.dataobject.CodeGenTableInfoDO;
import com.tomato.codegen.database.dataobject.CodegenDatasourceDO;
import com.tomato.codegen.dbtype.DbSelectService;
import com.tomato.codegen.dbtype.enums.DbTypeEnums;
import com.tomato.codegen.dbtype.utils.SimpleDataSource;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * MYSQl 数据源查询接口
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Service
public class MysqlSelectServiceImpl implements DbSelectService {

    @Override
    public DbTypeEnums dbType() {
        return DbTypeEnums.MYSQL;
    }

    @Override
    public CodeGenTableInfoDO tableInfo(String tableName, CodegenDatasourceDO codegenDatasourceDO) throws Exception{
        Connection connection = SimpleDataSource.getConnection(codegenDatasourceDO);
        QueryRunner qr = new QueryRunner();
        // 开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);

        String sql = "select table_name, table_comment from information_schema.tables " +
                "where table_schema = (select database()) and table_name = ? order by table_name asc;";
        Object[] params = {tableName};
        CodeGenTableInfoDO codeGenTableInfoDO = qr.query(connection, sql, new BeanHandler<>(CodeGenTableInfoDO.class,processor),params);
        DbUtils.closeQuietly(connection);
        return codeGenTableInfoDO;
    }

    @Override
    public List<CodeGenTableFieldDO> tableFields(String tableName, CodegenDatasourceDO codegenDatasourceDO) throws Exception {
        Connection connection = SimpleDataSource.getConnection(codegenDatasourceDO);
        QueryRunner qr = new QueryRunner();
        // 开启下划线->驼峰转换所用
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);

        String sql = "select table_schema,character_maximum_length,table_name,column_name, data_type, column_comment, column_key,is_nullable from information_schema.columns " +
                "where table_name = ? and table_schema = (select database()) order by ordinal_position";
        Object[] params = {tableName};
        List<CodeGenTableFieldDO> list = qr.query(connection, sql, new BeanListHandler<>(CodeGenTableFieldDO.class,processor),params);
        DbUtils.closeQuietly(connection);
        return list;
    }
}
