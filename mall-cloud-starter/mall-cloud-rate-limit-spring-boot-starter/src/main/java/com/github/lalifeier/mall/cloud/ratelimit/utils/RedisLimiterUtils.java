package com.github.lalifeier.mall.cloud.ratelimit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

public class RedisLimiterUtils {

  private static DefaultRedisScript<Long> limitScript;

  @Autowired
  public void setLimitScript(DefaultRedisScript<Long> limitScript) {
    RedisLimiterUtils.limitScript = limitScript;
  }

  @Autowired
  private StringRedisTemplate redisTemplate;

  private static Long executeLimitScript(String key, int bucketCapacity, long tokensPerSecond) {
    return new StringRedisTemplate().execute(limitScript, Collections.singletonList(key), bucketCapacity, tokensPerSecond);
  }

  /**
   * 获取令牌
   *
   * @param requestId       请求id
   * @param bucketCapacity  桶容量，即最大能同时承受多少的并发
   * @param tokensPerSecond 每秒生成多少的令牌
   * @return 如果获取到令牌则返回true，否则返回false
   */
  public static boolean tryAcquire(String requestId, int bucketCapacity, int tokensPerSecond) {
    return Long.valueOf(1).equals(executeLimitScript(requestId, bucketCapacity, tokensPerSecond));
  }
}


