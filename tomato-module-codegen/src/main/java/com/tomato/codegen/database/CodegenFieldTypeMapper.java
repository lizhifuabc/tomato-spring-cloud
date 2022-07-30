package com.tomato.codegen.database;

import com.tomato.codegen.database.dataobject.CodegenFieldTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字段类型管理
 *
 * @author lizhifu
 * @date 2022/7/29
 */
@Mapper
public interface CodegenFieldTypeMapper {
    /**
     * 查询所有字段类型
     * @return
     */
    @Select("SELECT * FROM codegen_field_type")
    List<CodegenFieldTypeDO> selectAll();

    /**
     * 根据字段类型查询字段类型
     * @param columnType
     * @return
     */
    @Select("SELECT * FROM codegen_field_type WHERE column_type = #{columnType}")
    CodegenFieldTypeDO selectByColumnType(@Param("columnType") String columnType);
}
