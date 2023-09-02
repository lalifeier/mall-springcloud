package com.github.lalifeier.mall.cloud.repeatsubmit.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.github.lalifeier.mall.cloud.repeatsubmit.aop.RepeatSubmitAspect;
import com.github.lalifeier.mall.cloud.repeatsubmit.properties.RepeatSubmitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
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
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    RedisSerializer<Object> serializer = redisSerializer();

    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(serializer);

    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(serializer);

    redisTemplate.afterPropertiesSet();

    return redisTemplate;
  }

  @Bean
  @ConditionalOnMissingBean
  public RedisSerializer<Object> redisSerializer() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL);
    Jackson2JsonRedisSerializer<Object> serializer =
        new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    return serializer;
  }
}
