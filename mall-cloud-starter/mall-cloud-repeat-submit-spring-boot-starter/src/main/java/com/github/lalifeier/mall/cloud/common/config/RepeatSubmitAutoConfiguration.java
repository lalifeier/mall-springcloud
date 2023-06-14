package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.aop.RepeatSubmitAspect;
import com.github.lalifeier.mall.cloud.common.properties.RepeatSubmitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(RepeatSubmitProperties.class)
public class RepeatSubmitAutoConfiguration {
  private final RepeatSubmitProperties properties;

  public RepeatSubmitAutoConfiguration(RepeatSubmitProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean
  public RepeatSubmitAspect repeatSubmitAspect(RedisTemplate<String, Object> redisTemplate) {
    return new RepeatSubmitAspect(redisTemplate);
  }

  @Bean
  @ConditionalOnMissingBean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }
}
