package com.tomato.auth.security.userdetails.sys;

import com.tomato.data.dto.response.Response;
import com.tomato.data.dto.response.SingleResponse;
import com.tomato.sys.api.SysUserFeignClient;
import com.tomato.sys.dto.clientObject.SysUserAuthRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Service
@Slf4j
public class SysUserDetailsService implements UserDetailsService {
    private final SysUserFeignClient sysUserFeignClient;

    public SysUserDetailsService(SysUserFeignClient sysUserFeignClient) {
        this.sysUserFeignClient = sysUserFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SingleResponse<SysUserAuthRep> response = sysUserFeignClient.getAuthInfoByUsername(username);
        SysUserDetails sysUserDetails = new SysUserDetails(response.getData());
        if (sysUserDetails == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else if (!sysUserDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!sysUserDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!sysUserDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return sysUserDetails;
    }
}
