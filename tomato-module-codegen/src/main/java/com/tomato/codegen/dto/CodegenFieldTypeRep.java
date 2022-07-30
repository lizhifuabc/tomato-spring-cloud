package com.tomato.codegen.dto;

import com.tomato.data.dto.Rep;
import lombok.Data;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Data
public class CodegenFieldTypeRep extends Rep {
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
}
