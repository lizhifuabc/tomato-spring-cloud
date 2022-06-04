package com.tomato.sys.dto.clientObject;

import com.tomato.data.dto.ClientObject;
import lombok.Data;

/**
 * OAuth2认证用户信息传输层对象
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Data
public class SysUserAuthRep extends ClientObject {
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
}
