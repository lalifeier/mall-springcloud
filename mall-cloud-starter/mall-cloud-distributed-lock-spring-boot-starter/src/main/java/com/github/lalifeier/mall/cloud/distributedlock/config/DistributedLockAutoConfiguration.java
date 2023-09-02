package com.github.lalifeier.mall.cloud.distributedlock.config;

import com.github.lalifeier.mall.cloud.distributedlock.aop.DistributedLockAspect;
import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DistributedLockAutoConfiguration {
  private static final String REDISSON_PREFIX = "redis://";
  @Resource
  private RedisProperties redisProperties;

  @Bean
  @ConditionalOnMissingBean(RedissonClient.class)
  public RedissonClient redissonClient() {
    Config config = new Config();
    String host = redisProperties.getHost();
    int port = redisProperties.getPort();
    config.useSingleServer().setAddress(REDISSON_PREFIX + host + ":" + port)
        .setDatabase(redisProperties.getDatabase()).setPassword(redisProperties.getPassword());
    return Redisson.create(config);
  }

  @Bean
  @ConditionalOnBean(RedissonClient.class)
  public DistributedLockAspect distributedLockAspect(RedissonClient redissonClient) {
    return new DistributedLockAspect(redissonClient);
  }
}
