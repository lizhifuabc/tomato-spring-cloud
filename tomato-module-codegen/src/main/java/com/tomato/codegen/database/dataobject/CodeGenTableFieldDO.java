package com.tomato.codegen.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 表字段信息
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Data
public class CodeGenTableFieldDO extends BaseDO {
    /**
     * 表 | 视图所在的数据库名称
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 类型
     */
    private String dataType;
    /**
     * 列说明
     */
    private String columnComment;
    /**
     * 以字符为单位的最大长度
     */
    private String characterMaximumLength;
    /**
     * 列上的索引类型 主键-->PRI  | 唯一索引 -->UNI  一般索引 -->MUL
     */
    private String columnKey;
    /**
     * 是否可以取空值
     */
    private Boolean isNullAble;
    public CodeGenTableFieldDO() {
    }
}
