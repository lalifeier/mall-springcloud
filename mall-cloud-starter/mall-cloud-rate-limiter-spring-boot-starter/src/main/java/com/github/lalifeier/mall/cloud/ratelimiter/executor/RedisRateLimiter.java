package com.github.lalifeier.mall.cloud.ratelimiter.executor;

import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.RateLimiterAlgorithm;
import com.github.lalifeier.mall.cloud.ratelimiter.algorithm.RateLimiterAlgorithmFactory;
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
    //List<String> keys = Collections.singletonList(key);

    AlgorithmTypeEnum algorithmType = rateLimiter.algorithmType();
    int capacity = rateLimiter.capacity();
    int rate = rateLimiter.rate();

    RateLimiterAlgorithm rateLimiterAlgorithm = RateLimiterAlgorithmFactory.getRateLimiterAlgorithm(algorithmType.getName());
    RedisScript<Long> script = rateLimiterAlgorithm.getScript();
    List<String> keys = rateLimiterAlgorithm.getKeys(key);
    List<String> scriptArgs = Arrays.asList();
    Long result = redisTemplate.execute(script, keys, scriptArgs);

    return (result != null && result == 1L);
  }

  private String doubleToString(final double param) {
    return String.valueOf(param);
  }
}
