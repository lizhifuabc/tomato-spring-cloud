redis.log(redis.LOG_WARNING,"leaky_bucket_rate_limiter start")
-- leaky_bucket_rate_limiter.{test}.tokens
local leaky_bucket_key = KEYS[1]
-- leaky_bucket_rate_limiter.{test}.timestamp
local last_bucket_key = KEYS[2]

redis.log(redis.LOG_WARNING, "leaky_bucket_key " .. leaky_bucket_key)
redis.log(redis.LOG_WARNING, "last_bucket_key " .. last_bucket_key)

-- 容量
local capacity = tonumber(ARGV[2])
-- 速率
local rate = tonumber(ARGV[1])
-- 请求数量
local requested = tonumber(ARGV[4])
-- 当前时间
local now = tonumber(ARGV[3])
redis.log(redis.LOG_WARNING, "capacity " .. ARGV[2])
redis.log(redis.LOG_WARNING, "rate " .. ARGV[1])
redis.log(redis.LOG_WARNING, "now " .. ARGV[3])
redis.log(redis.LOG_WARNING, "requested " .. ARGV[4])

-- 返回大于等于参数x的最小整数
-- key存活时间 = 容量 / 速率
local key_lifetime = math.ceil((capacity / rate) + 1)
redis.log(redis.LOG_WARNING, "key_lifetime " .. key_lifetime)

-- 桶内请求key的数量,记录所有的请求
-- get leaky_bucket_rate_limiter.{test}.tokens
local key_bucket_count = tonumber(redis.call("GET", leaky_bucket_key)) or 0

-- 桶的上次更新时间
-- get leaky_bucket_rate_limiter.{test}.timestamp
local last_time = tonumber(redis.call("GET", last_bucket_key)) or now

-- 当前时间 - 上次更新时间
local millis_since_last_leak = now - last_time

-- 桶允许流出的数量 =（当前时间 - 上次更新时间）* 速率
-- 2022年03月05日15:57:30 - 2022年03月05日15:57:26 = 4s
local leaks = millis_since_last_leak * rate

if leaks > 0 then
    -- 桶允许流出的数量 >= 桶内请求key的数量 ，此时可以全部漏出，清楚当前桶内请求数量
    if leaks >= key_bucket_count then
        -- 清除 key 请求数量
        key_bucket_count = 0
    else
        -- 桶允许流出的数量 < 桶内请求key的数量
        -- 桶内所有流出，剩余的 key 请求数量
        key_bucket_count = key_bucket_count - leaks
    end
    -- 桶更新时间 = 当前时间
    last_time = now
end

-- 是否可以继续请求
local is_allow = 0
-- 桶内当前请求 key 数量 = key 请求数量 + 请求数量（默认为 1 ）
local new_bucket_count = key_bucket_count + requested
-- 桶内当前请求 key 数量 <= 容量
if new_bucket_count <= capacity then
    is_allow = 1
else
    -- 桶内当前请求 key 数量 > 容量
    return {is_allow, new_bucket_count}
end

-- 为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值。
-- SETEX leaky_bucket_rate_limiter.{test}.tokens 10 
redis.call("SETEX", leaky_bucket_key, key_lifetime, new_bucket_count)

-- SETEX leaky_bucket_rate_limiter.{test}.timestamp 
redis.call("SETEX", last_bucket_key, key_lifetime, now)

-- return
return {is_allow, new_bucket_count}