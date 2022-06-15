package com.tomato.sys.controller;

import com.tomato.data.response.Response;
import com.tomato.data.response.SingleResponse;
import com.tomato.sys.dto.SysUserAddReq;
import com.tomato.sys.dto.clientObject.SysUserAuthRep;
import com.tomato.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统用户信息
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController {
    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 新增用户
     * @param sysUserAddReq
     * @return
     */
    @PostMapping("/add")
    public Response addUser(@Valid @RequestBody SysUserAddReq sysUserAddReq) {
        boolean result = sysUserService.saveUser(sysUserAddReq);
        return SingleResponse.of(sysUserAddReq);
    }

    /**
     * 根据用户名获取认证信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/username/{username}")
    public SingleResponse<SysUserAuthRep> getAuthInfoByUsername(@PathVariable(value = "username") String username) {
        SysUserAuthRep user = sysUserService.getAuthInfoByUsername(username);
        return SingleResponse.of(user);
    }
}
