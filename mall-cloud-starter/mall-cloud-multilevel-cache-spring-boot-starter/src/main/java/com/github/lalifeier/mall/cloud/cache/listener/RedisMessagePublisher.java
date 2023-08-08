package com.github.lalifeier.mall.cloud.cache.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessagePublisher implements MessagePublisher {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public void publish(String message) {
//    redisTemplate.convertAndSend(topic.getTopic(), message);
  }
}
