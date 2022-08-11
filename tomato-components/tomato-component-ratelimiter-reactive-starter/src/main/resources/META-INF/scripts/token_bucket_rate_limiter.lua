-- token_bucket_limiter.{test}.tokens
local tokens_key = KEYS[1]
-- token_bucket_limiter.{test}.timestamp
local timestamp_key = KEYS[2]
redis.log(redis.LOG_WARNING, "tokens_key " .. tokens_key)
redis.log(redis.LOG_WARNING, "timestamp_key " .. timestamp_key)

local rate = tonumber(ARGV[1])
local capacity = tonumber(ARGV[2])
local now = tonumber(ARGV[3])
local requested = tonumber(ARGV[4])

-- 填满令牌桶所需要的时间
-- 填充速度
local fill_time = capacity/rate
-- 返回小于或等于一个给定数字的最大整数
-- 令牌过期时间（重置桶内令牌的时间间隔） 填充时间*2
local ttl = math.floor(fill_time*2)

redis.log(redis.LOG_WARNING, "rate " .. ARGV[1])
redis.log(redis.LOG_WARNING, "capacity " .. ARGV[2])
redis.log(redis.LOG_WARNING, "now " .. now)
redis.log(redis.LOG_WARNING, "requested " .. ARGV[4])
redis.log(redis.LOG_WARNING, "filltime " .. fill_time)
redis.log(redis.LOG_WARNING, "ttl " .. ttl)

-- 剩余数量
local last_tokens = tonumber(redis.call("get", tokens_key))
if last_tokens == nil then
  last_tokens = capacity
end
redis.log(redis.LOG_WARNING, "last_tokens " .. last_tokens)

-- 上次更新时间
local last_refreshed = tonumber(redis.call("get", timestamp_key))
if last_refreshed == nil then
  last_refreshed = 0
end
redis.log(redis.LOG_WARNING, "last_refreshed " .. last_refreshed)

-- 返回最大的那个数字
-- 当前时间 - 上次更新时间（默认0）
local delta = math.max(0, now-last_refreshed)
-- 需要填充的数量 = delta（当前时间 - 上次更新时间（默认0）） * 速率 + 剩余 tokens
local filled_tokens = math.min(capacity, last_tokens+(delta*rate))
-- 需要填充的数量 >= 请求数量
local allowed = filled_tokens >= requested
local new_tokens = filled_tokens
local allowed_num = 0
if allowed then
  new_tokens = filled_tokens - requested
  allowed_num = 1
end

redis.log(redis.LOG_WARNING, "delta " .. delta)
redis.log(redis.LOG_WARNING, "filled_tokens " .. filled_tokens)
redis.log(redis.LOG_WARNING, "allowed_num " .. allowed_num)
redis.log(redis.LOG_WARNING, "new_tokens " .. new_tokens)

-- 设置新的桶的数量
redis.call("setex", tokens_key, ttl, new_tokens)
-- setex token_bucket_limiter.{test}.timestamp 过期时间 当前时间
redis.call("setex", timestamp_key, ttl, now)

-- return { allowed_num, new_tokens, capacity, filled_tokens, requested, new_tokens }
return { allowed_num, new_tokens }

