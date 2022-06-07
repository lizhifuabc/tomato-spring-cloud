package com.tomato.sys.dto;

import com.tomato.data.dto.req.Req;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 系统用户增加请求
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Data
public class SysUserAddReq extends Req {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
