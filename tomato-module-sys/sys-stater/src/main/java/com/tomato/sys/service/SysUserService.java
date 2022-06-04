package com.tomato.sys.service;

import com.tomato.sys.database.SysUserMapper;
import com.tomato.sys.database.dataobject.SysUserDO;
import com.tomato.sys.dto.SysUserAddReq;
import com.tomato.sys.dto.clientObject.SysUserAuthRep;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

/**
 * 系统用户操作
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Service
public class SysUserService {
    private final PasswordEncoder passwordEncoder;
    private final SysUserMapper sysUserMapper;

    public SysUserService(PasswordEncoder passwordEncoder, SysUserMapper sysUserMapper) {
        this.passwordEncoder = passwordEncoder;
        this.sysUserMapper = sysUserMapper;
    }

    public boolean saveUser(SysUserAddReq sysUserAddReq) {
        SysUserDO user = new SysUserDO();
        BeanUtils.copyProperties(sysUserAddReq, user);
        user.setPassword(passwordEncoder.encode(sysUserAddReq.getPassword()));
        // TODO ID 生成器
        user.setSysUserId(System.currentTimeMillis());
        sysUserMapper.insert(user);
        return false;
    }

    public SysUserAuthRep getAuthInfoByUsername(String username) {
        // TODO 权限相关信息
        SysUserDO sysUserDO = sysUserMapper.selectByUsername(username);
        SysUserAuthRep sysUserAuthRep = new SysUserAuthRep();
        BeanUtils.copyProperties(sysUserDO, sysUserAuthRep);
        return sysUserAuthRep;
    }
}
