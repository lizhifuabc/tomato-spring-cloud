package com.tomato.sys.api.fallback;

import com.tomato.data.dto.rep.ResponseCode;
import com.tomato.data.dto.rep.SingleResponse;
import com.tomato.sys.api.SysUserFeignClient;
import com.tomato.sys.dto.clientObject.SysUserAuthRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统用户
 *
 * @author lizhifu
 * @date 2022/6/2
 */
@Component
@Slf4j
public class SysUserFeignFallbackClient implements SysUserFeignClient {
    @Override
    public SingleResponse<SysUserAuthRep> getAuthInfoByUsername(String username) {
        log.error("feign远程调用系统用户服务异常后的降级方法");
        return SingleResponse.buildFailure(ResponseCode.FAILURE.getCode(),"feign远程调用系统用户服务异常后的降级方法");
    }
}
