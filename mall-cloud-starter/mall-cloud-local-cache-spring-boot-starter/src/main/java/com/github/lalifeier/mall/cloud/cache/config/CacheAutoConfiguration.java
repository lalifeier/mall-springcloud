package com.github.lalifeier.mall.cloud.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.lalifeier.mall.cloud.cache.properties.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@RefreshScope
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnClass(CacheManager.class)
//@EnableCaching
public class CacheAutoConfiguration {


//  @Bean
//  public Cache<String, Object> caffeineCache() {
//    return Caffeine.newBuilder()
//      // 设置最后一次写入或访问后经过固定时间过期
//      .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
//      // 初始的缓存空间大小
//      .initialCapacity(100)
//      // 缓存的最大条数
//      .maximumSize(maxSize)
//      .build();
//  }

  @Bean
  @ConditionalOnMissingBean
  public CacheManager cacheManager(CacheProperties properties) {
    List<CaffeineCache> caffeineCaches = new ArrayList<>();

    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(Caffeine.newBuilder()
      // 设置最后一次写入或访问后经过固定时间过期
      .expireAfterAccess(properties.getExpireAfterWrite(), TimeUnit.SECONDS)
      // 初始的缓存空间大小
//      .initialCapacity(100)
      // 缓存的最大条数
      .maximumSize(properties.getMaximumSize()));

    return cacheManager;
  }
}
