package com.tomato.codegen.dbtype.enums;

/**
 * 数据库类型
 *
 * @author lizhifu
 * @date 2022/7/30
 */
public enum DbTypeEnums {
    MYSQL("com.mysql.cj.jdbc.Driver"),
    ;
    private final String driverClass;

    DbTypeEnums(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDriverClass() {
        return driverClass;
    }
}
