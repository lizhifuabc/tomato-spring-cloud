package com.tomato.codegen.database;

import com.tomato.codegen.database.dataobject.CodegenDatasourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据源管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Mapper
public interface CodegenDatasourceMapper {
    /**
     * 查询所有数据源
     * @return
     */
    @Select("SELECT * FROM codegen_datasource")
    List<CodegenDatasourceDO> selectAll();

    /**
     * 根据数据源名称查询数据源
     * @param dbTypeName
     * @return
     */
    @Select("SELECT * FROM codegen_datasource WHERE db_type_name = #{dbTypeName}")
    CodegenDatasourceDO selectByDbTypeName(@Param("dbTypeName") String dbTypeName);
}
