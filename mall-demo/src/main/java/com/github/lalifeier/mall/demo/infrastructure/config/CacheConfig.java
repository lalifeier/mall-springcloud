//package com.github.lalifeier.mall.demo.infrastructure.config;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCache;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
////@RefreshScope
//@Configuration
//@EnableCaching
//public class CacheConfig {
//
//    //@Value(value = "${cache.expire:300}")
//    //private Integer expireAfterWrite;
//    //
//    //@Value(value = "${cache.max.size:100}")
//    //private Integer maxSize;
//
//    //@Bean
//    //public Cache<String, Object> caffeineCache() {
//    //    return Caffeine.newBuilder()
//    //            // 设置最后一次写入或访问后经过固定时间过期
//    //            .expireAfterWrite(60, TimeUnit.SECONDS)
//    //            // 初始的缓存空间大小
//    //            .initialCapacity(100)
//    //            // 缓存的最大条数
//    //            .maximumSize(1000)
//    //            .build();
//    //}
//
//    @Bean("caffeineCacheManager")
//    public CacheManager cacheManager() {
//        List<CaffeineCache> caffeineCaches = new ArrayList<>();
//
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.setCaffeine(Caffeine.newBuilder()
//                // 设置最后一次写入或访问后经过固定时间过期
//                .expireAfterAccess(60, TimeUnit.SECONDS)
//                // 初始的缓存空间大小
//                .initialCapacity(100)
//                // 缓存的最大条数
//                .maximumSize(1000));
//        return cacheManager;
//    }
//
//}
