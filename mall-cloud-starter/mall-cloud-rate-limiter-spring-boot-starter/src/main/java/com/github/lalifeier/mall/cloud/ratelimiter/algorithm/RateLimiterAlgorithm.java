package com.github.lalifeier.mall.cloud.ratelimiter.algorithm;

import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;

public interface RateLimiterAlgorithm<T> {
  String getScriptName();

  RedisScript<T> getScript();

  List<String> getKeys(String key);
}
