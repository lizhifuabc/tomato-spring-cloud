package com.tomato.codegen.service.impl;

import com.tomato.codegen.database.CodegenFieldTypeMapper;
import com.tomato.codegen.database.dataobject.CodegenFieldTypeDO;
import com.tomato.codegen.dto.CodegenFieldTypeRep;
import com.tomato.codegen.service.CodegenFieldTypeService;
import com.tomato.web.utils.BeanUtilsWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Service
public class CodegenFieldTypeServiceImpl implements CodegenFieldTypeService {
    private final CodegenFieldTypeMapper codegenFieldTypeMapper;

    public CodegenFieldTypeServiceImpl(CodegenFieldTypeMapper codegenFieldTypeMapper) {
        this.codegenFieldTypeMapper = codegenFieldTypeMapper;
    }

    @Override
    public List<CodegenFieldTypeRep> listAll() {
        List<CodegenFieldTypeDO> codegenFieldTypeDOS = codegenFieldTypeMapper.selectAll();
        List<CodegenFieldTypeRep> codegenFieldTypeReps = codegenFieldTypeDOS.stream().map(codegenFieldTypeDO -> {
            CodegenFieldTypeRep codegenFieldTypeRep = new CodegenFieldTypeRep();
            BeanUtilsWrapper.copyToDo(codegenFieldTypeDO, codegenFieldTypeRep);
            return codegenFieldTypeRep;
        }).collect(java.util.stream.Collectors.toList());
        return codegenFieldTypeReps;
    }

    @Override
    public CodegenFieldTypeRep getByColumnType(String columnType) {
        CodegenFieldTypeDO codegenFieldTypeDO = codegenFieldTypeMapper.selectByColumnType(columnType);
        CodegenFieldTypeRep codegenFieldTypeRep = new CodegenFieldTypeRep();
        BeanUtilsWrapper.copyToDo(codegenFieldTypeDO, codegenFieldTypeRep);
        return codegenFieldTypeRep;
    }
}
