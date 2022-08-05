package com.tomato.codegen;

import com.tomato.codegen.database.CodegenDatasourceMapper;
import com.tomato.codegen.database.dataobject.CodeGenTableFieldDO;
import com.tomato.codegen.database.dataobject.CodeGenTableInfoDO;
import com.tomato.codegen.database.dataobject.CodegenDatasourceDO;
import com.tomato.codegen.dbtype.impl.MysqlSelectServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * MysqlSelectServiceTest
 *
 * @author lizhifu
 * @date 2022/8/5
 */
@SpringBootTest
public class MysqlSelectServiceImplTest {
    @Resource
    private MysqlSelectServiceImpl mysqlSelectServiceImpl;
    @Resource
    private CodegenDatasourceMapper codegenDatasourceMapper;
    @Test
    public void test() throws Exception {
        CodegenDatasourceDO codegenDatasourceDO = codegenDatasourceMapper.selectByDbTypeName("tomato_codegen");
        CodeGenTableInfoDO pay_info = mysqlSelectServiceImpl.tableInfo("codegen_datasource", codegenDatasourceDO);
        System.out.println(pay_info);

        List<CodeGenTableFieldDO> list = mysqlSelectServiceImpl.tableFields("codegen_datasource", codegenDatasourceDO);
        System.out.println(list);
    }
}
