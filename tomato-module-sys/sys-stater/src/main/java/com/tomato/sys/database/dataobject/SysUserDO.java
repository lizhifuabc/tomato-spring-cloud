package com.tomato.sys.database.dataobject;

import com.tomato.data.dataobject.BaseDO;
import lombok.Data;

/**
 * 系统用户信息
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Data
public class SysUserDO extends BaseDO {
    /**
    * 系统用户ID
    */
    private Long sysUserId;

    /**
    * 用户名
    */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Integer state;
    /**
     * 逻辑删除标识：0-未删除；1-已删除
     */
    private Integer deleted;
}
