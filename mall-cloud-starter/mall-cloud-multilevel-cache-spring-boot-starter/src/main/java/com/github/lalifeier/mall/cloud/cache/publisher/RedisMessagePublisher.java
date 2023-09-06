package com.github.lalifeier.mall.cloud.cache.publisher;

import com.github.lalifeier.mall.cloud.cache.event.CacheMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher<CacheMessage> {

    private RedisTemplate<Object, Object> redisTemplate;

    private ChannelTopic channelTopic;

    public RedisMessagePublisher(RedisTemplate<Object, Object> redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    @Override
    public void publish(CacheMessage data) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), data);
    }
}
