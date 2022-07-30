package com.tomato.codegen.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/29
 */
@Data
public class CodegenFieldTypeDO extends BaseDO {
    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 属性类型
     */
    private String columnJavaType;

    /**
     * 属性所在包
     */
    private String columnJavaPac;

    public CodegenFieldTypeDO() {}
}
