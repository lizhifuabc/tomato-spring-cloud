package com.tomato.sys.database;

import com.tomato.sys.database.dataobject.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户信息
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Mapper
public interface SysUserMapper {
    /**
     * 查询系统用户信息
     * @param sysUserId
     * @return
     */
    SysUserDO selectBySysUserId(Long sysUserId);

    /**
     * 新增系统用户信息
     * @param user
     */
    void insert(SysUserDO user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUserDO selectByUsername(String username);
}
