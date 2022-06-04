package com.tomato.sys.api;

import com.tomato.data.dto.response.SingleResponse;
import com.tomato.sys.api.fallback.SysUserFeignFallbackClient;
import com.tomato.sys.dto.clientObject.SysUserAuthRep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统用户
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@FeignClient(value = "tomato-module-sys", fallback = SysUserFeignFallbackClient.class)
public interface SysUserFeignClient {
    @RequestMapping(value = "api/sys/user/username/{username}", method = RequestMethod.GET)
    SingleResponse<SysUserAuthRep> getAuthInfoByUsername(@PathVariable(value = "username") String username);
}
