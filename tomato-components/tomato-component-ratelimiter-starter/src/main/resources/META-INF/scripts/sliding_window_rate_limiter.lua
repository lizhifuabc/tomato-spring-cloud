redis.log(redis.LOG_WARNING,"sliding_window_rate_limiter start")
-- tokens_key sliding_window_rate_limiter.{127.0.1}.tokens
local tokens_key = KEYS[1]
-- 随机数
-- tokens_key b7d153abe716439faaac435330eeceb7 
local timestamp_key = KEYS[2]

redis.log(redis.LOG_WARNING, "tokens_key " .. tokens_key)
redis.log(redis.LOG_WARNING, "timestamp_key " .. timestamp_key)

local rate = tonumber(ARGV[1])
local capacity = tonumber(ARGV[2])
local now = tonumber(ARGV[3])

-- 窗口大小 = 容量/速率
local window_size = tonumber(capacity / rate)
local window_time = 1

redis.log(redis.LOG_WARNING, "rate " .. ARGV[1])
redis.log(redis.LOG_WARNING, "capacity " .. ARGV[2])
redis.log(redis.LOG_WARNING, "now " .. ARGV[3])
redis.log(redis.LOG_WARNING, "window_size " .. window_size)

local last_requested = 0
-- exists sliding_window_rate_limiter.{127.0.1}.tokens
-- 检查给定 key 是否存在
local exists_key = redis.call('exists', tokens_key)
if (exists_key == 1) then
    -- 存在，获取请求数量
    -- zcard sliding_window_rate_limiter.{127.0.1}.tokens
    last_requested = redis.call('zcard', tokens_key)
end
redis.log(redis.LOG_WARNING, "last_requested " .. last_requested)

-- 剩余请求数 = 容量 - 已经请求数量
local remain_request = capacity - last_requested
local allowed_num = 0
-- 可以继续请求
if (last_requested < capacity) then
    allowed_num = 1
    -- ZADD KEY_NAME SCORE1 VALUE1.. SCOREN VALUEN
    -- zadd sliding_window_rate_limiter.{127.0.1}.tokens 1.646461938E9 1
    -- zadd sliding_window_rate_limiter.{127.0.1}.tokens 1.646461937E9 2
    redis.call('zadd', tokens_key, now, timestamp_key)
end

redis.log(redis.LOG_WARNING, "remain_request " .. remain_request)
redis.log(redis.LOG_WARNING, "allowed_num " .. allowed_num)
redis.log(redis.LOG_WARNING, "allowed_num " .. allowed_num)
redis.log(redis.LOG_WARNING, "now - window_size / window_time " .. now - window_size / window_time)
-- 移除有序集中，指定分数（score）区间内的所有成员。
-- zremrangebyscore sliding_window_rate_limiter.{127.0.1}.tokens 0 1646465177
redis.call('zremrangebyscore', tokens_key, 0, now - window_size / window_time)
-- 窗口大小之后清除数据
-- expire sliding_window_rate_limiter.{127.0.1}.tokens 10
redis.call('expire', tokens_key, window_size)

return { allowed_num, remain_request }

