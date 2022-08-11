-- 例如传入参数，限流key 127.0.0.1
-- 拼接之后传入redis key1 为：concurrent_rate_limiter.{127.0.0.1}.tokens
local tokens = KEYS[1]
-- 拼接之后传入redis key2 为随机数
-- cc18c0499aaa4108bfb779544cdfcfe7
local tokens_timestamp= KEYS[2]
-- 这里的打印日志级别，需要和redis.conf配置文件中的日志设置级别一致才行
-- redis.log(redis.LOG_WARNING,tokens)
-- 总容量
local capacity = tonumber(ARGV[2])
-- 当前时间
local current_timestamp = tonumber(ARGV[3])
--当 tokens 存在且是有序集类型时，返回有序集的基数。 当 tokens 不存在时，返回 0
-- zcard concurrent_rate_limiter.{127.0.0.1}.tokens
local count = redis.call("zcard", tokens)
local allowed = 0

--没有达到总容量
if count < capacity then
  -- 将一个或多个成员元素及其分数值加入到有序集当中
  -- 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置
  -- ZADD KEY_NAME SCORE1 VALUE1.. SCOREN VALUEN
  -- zadd concurrent_rate_limiter.{127.0.0.1}.tokens 1.646461938E9 1
  -- zadd concurrent_rate_limiter.{127.0.0.1}.tokens 1.646463249E9 2
  -- zadd concurrent_rate_limiter.{127.0.0.1}.tokens 1.646463249E9 cc18c0499aaa4108bfb779544cdfcfe7
  -- 显示整个有序集成员
  -- ZRANGE concurrent_rate_limiter.{127.0.0.1}.tokens 0 -1 WITHSCORES
  redis.call("zadd", tokens, current_timestamp, tokens_timestamp)
  allowed = 1
  count = count + 1
end
-- 删除本次请求
-- ZREM concurrent_rate_limiter.{127.0.0.1}.tokens cc18c0499aaa4108bfb779544cdfcfe7
-- redis.call("ZREM", tokens, tokens_timestamp)

return { allowed, capacity - count }