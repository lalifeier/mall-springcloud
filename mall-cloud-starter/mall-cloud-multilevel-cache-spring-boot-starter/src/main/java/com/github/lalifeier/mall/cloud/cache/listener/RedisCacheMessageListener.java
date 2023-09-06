package com.github.lalifeier.mall.cloud.cache.listener;

import com.github.lalifeier.mall.cloud.cache.event.CacheMessage;
import jakarta.annotation.Resource;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class RedisCacheMessageListener implements MessageListener {

    @Resource
    private RedisTemplate redisTemplate;

    private CaffeineCache caffeineCache;

    public CaffeineCache getCaffeineCache() {
        return caffeineCache;
    }

    public void setCaffeineCache(CaffeineCache caffeineCache) {
        this.caffeineCache = caffeineCache;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("监听的redis message: {}" + message.toString());
        CacheMessage cacheMessage =
                (CacheMessage) redisTemplate.getValueSerializer().deserialize(message.getBody());
        if (Objects.isNull(cacheMessage.getKey())) {
            caffeineCache.invalidate();
        } else {
            caffeineCache.evict(cacheMessage.getKey());
        }
    }
}
