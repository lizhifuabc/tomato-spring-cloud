package com.tomato.codegen.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 数据源管理
 *
 * @author lizhifu
 * @date 2022/7/30
 */
@Data
public class CodegenDatasourceDO extends BaseDO {
    /**
     * 数据库类型 mysql
     */
    private String dbType;

    /**
     * 别名
     */
    private String dbTypeName;

    /**
     * url
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    public CodegenDatasourceDO() {}
}
