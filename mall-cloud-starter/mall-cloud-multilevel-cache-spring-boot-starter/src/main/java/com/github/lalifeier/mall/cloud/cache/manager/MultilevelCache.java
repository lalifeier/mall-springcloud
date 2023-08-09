package com.github.lalifeier.mall.cloud.cache.manager;


import com.github.lalifeier.mall.cloud.cache.event.CacheMessage;
import com.github.lalifeier.mall.cloud.cache.publisher.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.Callable;

@Slf4j
public class MultilevelCache extends AbstractValueAdaptingCache {

  /**
   * 缓存名称
   */
  private String cacheName;


  /**
   * 默认开启一级缓存
   */
  private final boolean enableFirstCache;

  /**
   * 一级缓存
   */
  private final CaffeineCache caffeineCache;

  /**
   * 二级缓存
   */
  private final RedisCache redisCache;


  private final MessagePublisher redisMessagePublisher;

  private ThreadPoolTaskExecutor executor;


  public MultilevelCache(boolean allowNullValues, boolean enableFirstCache, String cacheName, RedisCache redisCache, CaffeineCache caffeineCache, MessagePublisher redisMessagePublisher) {
    super(allowNullValues);
    this.cacheName = cacheName;
    this.enableFirstCache = enableFirstCache;
    this.redisCache = redisCache;
    this.caffeineCache = caffeineCache;
    this.redisMessagePublisher = redisMessagePublisher;
    this.executor = createThreadPoolTaskExecutor();
  }

  private ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("MultilevelCache-");
    executor.setDaemon(true);
    executor.initialize();
    return executor;
  }

  @Override
  public String getName() {
    return cacheName;
  }

  @Override
  public Object getNativeCache() {
    return null;
  }

  @Override
  public <T> T get(Object key, Callable<T> valueLoader) {
    Object value = lookup(key);
    return (T) value;
  }

  @Override
  public void put(@NonNull Object key, Object value) {
    redisCache.put(key, value);
    if (enableFirstCache) {
      asyncPublish(key, value);
    }
  }

  @Override
  public ValueWrapper putIfAbsent(@NonNull Object key, Object value) {
    ValueWrapper valueWrapper = redisCache.putIfAbsent(key, value);
    if (enableFirstCache) {
      asyncPublish(key, value);
    }
    return valueWrapper;
  }


  @Override
  public void evict(Object key) {
    redisCache.evict(key);
    if (enableFirstCache) {
      asyncPublish(key, null);
    }
  }

  @Override
  public boolean evictIfPresent(Object key) {
    return false;
  }

  @Override
  public void clear() {
    redisCache.clear();
    if (enableFirstCache) {
      asyncPublish(null, null);
    }
  }


  @Override
  protected Object lookup(Object key) {
    Assert.notNull(key, "key不可为空");
    ValueWrapper value;
    if (enableFirstCache) {
      // 尝试从一级缓存中获取值
      value = caffeineCache.get(key);
      if (Objects.nonNull(value)) {
        log.info("查询caffeine 一级缓存 key:{}, 返回值是:{}", key, value.get());
        return value.get();
      }
    }

    // 从二级缓存中获取值
    value = redisCache.get(key);
    if (Objects.nonNull(value)) {
      log.info("查询redis 二级缓存 key:{}, 返回值是:{}", key, value.get());
      // 异步将二级缓存redis写入一级缓存caffeine
      if (enableFirstCache) {
        ValueWrapper finalValue = value;
        caffeineCache.put(key, finalValue.get());
      }
      return value.get();
    }
    return null;
  }

  /**
   * 异步发布缓存变更消息，通知其他节点清理本地缓存
   * 这样可以防止本地缓存中存储脏数据
   *
   * @param key   缓存键
   * @param value 缓存值
   */
  void asyncPublish(Object key, Object value) {
    CacheMessage cacheMessage = new CacheMessage();
    cacheMessage.setCacheName(cacheName);
    cacheMessage.setKey(key);
    cacheMessage.setValue(value);
    redisMessagePublisher.publish(cacheMessage);
  }
}

