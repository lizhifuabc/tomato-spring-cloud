package com.tomato.ratelimiter.algorithm;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Arrays;
import java.util.List;

/**
 * 算法抽象类
 *
 * @author lizhifu
 * @date 2022/3/4
 */
public abstract class AbstractRateLimiterAlgorithm implements RateLimiterAlgorithm<List<Long>>{
    /**
     * 脚本名称
     */
    private final String scriptName;
    /**
     * RedisScript 脚本加载
     */
    private final RedisScript<List<Long>> script;
    /**
     * 脚本位置
     */
    private final String SCRIPT_PATH = "/META-INF/scripts/";

    protected AbstractRateLimiterAlgorithm(final String scriptName) {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        String scriptPath = SCRIPT_PATH + scriptName + ".lua";
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPath)));
        redisScript.setResultType(List.class);

        this.script = redisScript;
        this.scriptName = scriptName;
    }

    @Override
    public RedisScript<List<Long>> getScript() {
        return script;
    }

    public String getScriptName() {
        return scriptName;
    }

    /**
     * 拼接key的时候，对key使用了大括号{}进行了包裹，
     * 这是因为lua脚本执行成功的前提条件是所用使用到的redis健值必须在一个hash槽中，
     * 使用大括号对key进行包裹后，redis在对key进行hash时，只会hash大括号内部的字符，
     * 这样就可以保证lua脚本中的使用的key-value在同一个槽内。
     * 这样就确保了cluster模式下正常执行redis-lua脚本，但是需要注意的是，
     * 这里大括号内包裹的内容不能是不变的，如果是不变的话，会有大量的key-value被分配到同一个槽里，
     * 导致hash倾斜，key-value分布不均匀。
     *
     * @param key
     * @return
     */
    @Override
    public List<String> getKeys(final String key) {
        String prefix = scriptName + ".{" + key;
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }
}
