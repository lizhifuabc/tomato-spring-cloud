package com.tomato.codegen.dbtype.utils;

import com.tomato.codegen.database.dataobject.CodegenDatasourceDO;
import com.tomato.codegen.dbtype.enums.DbTypeEnums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库，无连接池，不用关闭连接
 *
 * @author lizhifu
 * @date 2022/8/1
 */
public class SimpleDataSource {
    /**
     * 连接超时时间
     */
    private static final int CONNECTION_TIMEOUTS_SECONDS = 6;
    public static Connection getConnection(CodegenDatasourceDO codegenDatasourceDO) throws Exception{
        DriverManager.setLoginTimeout(CONNECTION_TIMEOUTS_SECONDS);
        Class.forName(DbTypeEnums.MYSQL.getDriverClass());
        return DriverManager.getConnection(codegenDatasourceDO.getUrl(), codegenDatasourceDO.getUsername(), codegenDatasourceDO.getPassword());
    }
}
