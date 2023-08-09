package com.github.lalifeier.mall.cloud.cache.manager;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

public class CustomizedRedisCache extends RedisCache {

  public CustomizedRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
    super(name, cacheWriter, cacheConfig);
  }
}
