package com.github.lalifeier.mall.cloud.ratelimiter.executor;

import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.RateLimiterAlgorithm;
import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.factory.RateLimiterAlgorithmFactory;
import com.github.lalifeier.mall.cloud.ratelimiter.annotation.RateLimiter;
import com.github.lalifeier.mall.cloud.ratelimiter.enums.AlgorithmTypeEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RedisRateLimiter {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisRateLimiter(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean acquire(String key, RateLimiter rateLimiter) {
    int capacity = rateLimiter.capacity();
    int rate = rateLimiter.rate();
    int perSecond = rateLimiter.perSecond();

    AlgorithmTypeEnum algorithmType = rateLimiter.algorithmType();
    RateLimiterAlgorithm rateLimiterAlgorithm = RateLimiterAlgorithmFactory.getRateLimiterAlgorithm(algorithmType.getName());
    RedisScript<List<Long>> script = rateLimiterAlgorithm.getScript();
    List<String> keys = rateLimiterAlgorithm.getKeys(key);
    List<String> scriptArgs = Arrays.asList(String.valueOf(rate), String.valueOf(capacity), String.valueOf(System.currentTimeMillis() / 1000), String.valueOf(perSecond));
//    List<Long> results = redisTemplate.execute(script, keys, scriptArgs.toArray());
//
//
//    boolean allowed = results.get(0) == 1L;
//    Long tokensLeft = results.get(1);

    return false;
  }
}
