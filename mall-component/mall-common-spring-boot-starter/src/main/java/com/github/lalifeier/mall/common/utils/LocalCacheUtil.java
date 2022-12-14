package com.github.lalifeier.mall.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
@EnableCaching
public class LocalCacheUtil {

    @Autowired
    private CacheManager cacheManager;

    /**
     * 加入缓存
     *
     * @param key       键值
     * @param value     缓存值
     * @param cacheName 缓存名称
     */
    public void put(Object key, Object value, String cacheName) {
        getCacheName(cacheName).put(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key       键值
     * @param cacheName 缓存名称
     * @return
     */
    public String get(Object key, String cacheName) {
        Cache.ValueWrapper value = getCacheName(cacheName).get(key);
        return String.valueOf(value.get());
    }

    /**
     * 获取缓存
     *
     * @param key       键值
     * @param type      缓存值的类型
     * @param cacheName 缓存名称
     * @param <T>
     * @return
     */
    public <T> T get(Object key, Class<T> type, String cacheName) {
        return getCacheName(cacheName).get(key, type);
    }

    /**
     * 获取缓存
     *
     * @param key         键值
     * @param valueLoader 缓存值的类型
     * @param cacheName   缓存名称
     * @param <T>
     * @return
     */
    public <T> T get(Object key, Callable<T> valueLoader, String cacheName) {
        return getCacheName(cacheName).get(key, valueLoader);
    }

    /**
     * 删除缓存
     *
     * @param key       键值
     * @param cacheName 缓存名称
     * @return
     */
    public Boolean evictIfPresent(Object key, String cacheName) {
        return getCacheName(cacheName).evictIfPresent(key);
    }

    /**
     * 清除缓存名称下的全部缓存
     *
     * @param cacheName 缓存名称
     * @return
     */
    public Boolean invalidate(String cacheName) {
        return getCacheName(cacheName).invalidate();
    }

    /**
     * 根据名称获取缓存对象
     *
     * @param cacheName 缓存名称
     * @return
     */
    public Cache getCacheName(String cacheName) {
        return cacheManager.getCache(cacheName);
    }
}
