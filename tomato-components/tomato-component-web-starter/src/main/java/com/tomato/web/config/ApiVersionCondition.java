package com.tomato.web.config;

import com.tomato.web.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API版本控制
 *
 * @author lizhifu
 * @date 2021/12/10
 */
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    /**
     * 接口路径中的版本号前缀，如: api/v[1-n]/test
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v([0-9]+\\.{0,1}[0-9]{0,2})/");
    private ApiVersion apiVersion;

    public ApiVersionCondition(ApiVersion apiVersion) {
        this.apiVersion = apiVersion;
    }

    public ApiVersion getApiVersion() {
        return apiVersion;
    }

    /**
     * 最近优先原则，方法定义的 @ApiVersion > 类定义的 @ApiVersion
     * @param other
     * @return
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        log.info("uri is {}",httpServletRequest.getRequestURI());
        Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if (m.find()) {
            double version = Double.valueOf(m.group(1));
            if (version >= getApiVersion().value()) {
                return this;
            }
        }
        return null;
    }

    /**
     * 当出现多个符合匹配条件的ApiVersionCondition，优先匹配版本号较大的
     * @param other
     * @param request
     * @return
     */
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion().value() >= getApiVersion().value() ? 1 : -1;
    }
}
