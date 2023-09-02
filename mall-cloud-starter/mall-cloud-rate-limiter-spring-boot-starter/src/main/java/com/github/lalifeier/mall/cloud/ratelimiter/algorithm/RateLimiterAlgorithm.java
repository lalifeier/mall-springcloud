package com.github.lalifeier.mall.cloud.ratelimiter.algorithm;

import java.util.List;
import org.springframework.data.redis.core.script.RedisScript;

public interface RateLimiterAlgorithm<T> {
  String getScriptName();

  RedisScript<T> getScript();

  List<String> getKeys(String key);
}
