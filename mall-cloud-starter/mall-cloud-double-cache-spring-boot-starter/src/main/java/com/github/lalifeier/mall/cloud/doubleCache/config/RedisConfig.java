package com.github.lalifeier.mall.cloud.doubleCache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    // json序列化设置

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
      ObjectMapper.DefaultTyping.NON_FINAL,//类名序列化到json串中
      JsonTypeInfo.As.WRAPPER_ARRAY);

//        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer
      = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

    //String类型的序列化
    RedisSerializer<?> stringSerializer = new StringRedisSerializer();

    redisTemplate.setKeySerializer(stringSerializer);// key采用String序列化方式
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
    redisTemplate.setHashKeySerializer(stringSerializer);// Hash key采用String序列化方式
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

}
