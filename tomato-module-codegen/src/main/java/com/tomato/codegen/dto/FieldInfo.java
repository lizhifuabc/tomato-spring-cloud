package com.tomato.codegen.dto;

import lombok.Data;

/**
 * 属性信息
 *
 * @author lizhifu
 * @date 2022/8/5
 */
@Data
public class FieldInfo {
    private String fieldName;
    private String fieldClass;
    private String fieldComment;
}
