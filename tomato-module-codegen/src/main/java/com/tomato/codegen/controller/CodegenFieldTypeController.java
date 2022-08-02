package com.tomato.codegen.controller;

import com.tomato.codegen.dto.CodegenFieldTypeRep;
import com.tomato.codegen.service.CodegenFieldTypeService;
import com.tomato.data.response.MultiResponse;
import com.tomato.data.response.SingleResponse;
import com.tomato.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@RestController
@RequestMapping("/codegen/fieldType")
public class CodegenFieldTypeController extends BaseController {
    private final CodegenFieldTypeService codegenFieldTypeService;

    public CodegenFieldTypeController(CodegenFieldTypeService codegenFieldTypeService) {
        this.codegenFieldTypeService = codegenFieldTypeService;
    }
    /**
     * 查询所有字段类型
     * @return
     */
    @RequestMapping("/listAll")
    public MultiResponse<CodegenFieldTypeRep> listAll() {
        return MultiResponse.of(codegenFieldTypeService.listAll());
    }
    /**
     * 根据字段类型查询字段类型
     * @param columnType
     * @return
     */
    @RequestMapping("/getByColumnType")
    public SingleResponse<CodegenFieldTypeRep> getByColumnType(String columnType) {
        return SingleResponse.of(codegenFieldTypeService.getByColumnType(columnType));
    }
}
