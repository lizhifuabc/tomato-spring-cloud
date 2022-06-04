package com.tomato.sys;

import com.tomato.sys.dto.SysUserAddReq;
import com.tomato.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * SysUserService
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@SpringBootTest
public class SysUserServiceTest {
    @Resource
    private SysUserService sysUserService;
    @Test
    public void test() {
        SysUserAddReq sysUserAddReq = new SysUserAddReq();
        sysUserAddReq.setPassword("123456");
        sysUserAddReq.setUsername("lizhifu");
        sysUserService.saveUser(sysUserAddReq);
    }
}
