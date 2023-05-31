package com.github.lalifeier.mall.doubleCache.config;

import com.github.lalifeier.mall.doubleCache.cache.DoubleCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@EnableCaching
@Configuration
public class CacheConfig {
    @Autowired
    DoubleCacheConfig doubleCacheConfig;

    @Bean
    public DoubleCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate,
                                           DoubleCacheConfig doubleCacheConfig) {
        return new DoubleCacheManager(redisTemplate, doubleCacheConfig);
    }
}

