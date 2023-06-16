package com.github.lalifeier.mall.cloud.ratelimit.limit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.List;

public class RateLimiter {

  private final RedisTemplate<String, Object> redisTemplate;
  private final DefaultRedisScript<Long> limitScript;

  public RateLimiter(RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> limitScript) {
    this.redisTemplate = redisTemplate;
    this.limitScript = limitScript;
  }

  /**
   * 尝试获取令牌并进行限流处理
   *
   * @param key             令牌桶键名
   * @param bucketCapacity  令牌桶容量
   * @param tokensPerSecond 每秒生成的令牌数目
   * @return 如果获取到令牌则返回true，否则返回false
   */
  public boolean tryAcquire(String key, int bucketCapacity, double tokensPerSecond) {
    List<String> keys = Collections.singletonList(key);
    Long result = redisTemplate.execute(limitScript, keys, bucketCapacity, tokensPerSecond);

    return (result != null && result == 1L);
  }
}
