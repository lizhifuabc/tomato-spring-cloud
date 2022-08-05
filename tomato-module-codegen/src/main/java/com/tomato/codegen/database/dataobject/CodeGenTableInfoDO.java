package com.tomato.codegen.database.dataobject;

import lombok.Data;

/**
 * 表信息
 *
 * @author lizhifu
 * @date 2022/8/5
 */
public class CodeGenTableInfoDO {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表名备注
     */
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public String toString() {
        return "CodeGenTableInfoDO{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                '}';
    }

    public CodeGenTableInfoDO(){
    }
}
