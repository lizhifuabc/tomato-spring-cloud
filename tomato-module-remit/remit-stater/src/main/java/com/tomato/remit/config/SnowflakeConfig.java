package com.tomato.remit.config;

import com.tomato.utils.IdUtil;
import com.tomato.utils.lang.Snowflake;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Snowflake
 *
 * @author lizhifu
 * @date 2022/7/6
 */
@Configuration
public class SnowflakeConfig {
    @Resource
    private InetUtils inetUtils;
    @Bean
    public Snowflake snowflake() {
        long dataCenterId = IdUtil.getDataCenterId(31, inetUtils.findFirstNonLoopbackAddress());
        long workerId = IdUtil.getWorkerId(dataCenterId, 31);
        return Snowflake.create(workerId, dataCenterId);
    }
}
