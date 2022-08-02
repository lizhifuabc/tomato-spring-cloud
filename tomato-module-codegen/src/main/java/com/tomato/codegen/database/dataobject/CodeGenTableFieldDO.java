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
    private String columnType;
    /**
     * 列说明
     */
    private String columnComment;
    /**
     * 列说明
     */
    @TableField(exist = false)
    private String comment;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性类型
     */
    private String attrType;
    /**
     * 属性包名
     */
    private String packageName;
    /**
     * 是否主键 0：否  1：是
     */
    private boolean isPk;
    /**
     * 是否必填 0：否  1：是
     */
    private boolean isRequired;
    /**
     * 是否表单字段 0：否  1：是
     */
    private boolean isForm;
    /**
     * 是否列表字段 0：否  1：是
     */
    private boolean isList;
    /**
     * 是否查询字段 0：否  1：是
     */
    private boolean isQuery;
    /**
     * 查询方式
     */
    private String queryType;
    /**
     * 表单类型
     */
    private String formType;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 效验方式
     */
    private String validatorType;
    /**
     * 排序
     */
    private Integer sort;
}
