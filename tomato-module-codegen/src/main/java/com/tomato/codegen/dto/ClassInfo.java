package com.tomato.codegen.dto;

import lombok.Data;

import java.util.List;

/**
 * 类信息
 *
 * @author lizhifu
 * @date 2022/8/5
 */
@Data
public class ClassInfo {
    /**
     * 类名
     */
    private String className;
    /**
     * 类名备注
     */
    private String classComment;
    /**
     * 类所在包
     */
    private String classPac;

    private List<FieldInfo> fieldList;
}
