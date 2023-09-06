package com.github.lalifeier.mall.cloud.cache.config;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.lalifeier.mall.cloud.cache.listener.CaffeineCacheRemovalListener;
import com.github.lalifeier.mall.cloud.cache.listener.RedisCacheMessageListener;
import com.github.lalifeier.mall.cloud.cache.manager.CustomizedRedisCache;
import com.github.lalifeier.mall.cloud.cache.manager.MultilevelCache;
import com.github.lalifeier.mall.cloud.cache.properties.MultilevelCacheProperties;
import com.github.lalifeier.mall.cloud.cache.publisher.MessagePublisher;
import com.github.lalifeier.mall.cloud.cache.publisher.RedisMessagePublisher;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(MultilevelCacheProperties.class)
public class MultilevelCacheAutoConfiguration {

    @Resource
    private MultilevelCacheProperties multilevelCacheProperties;

    @Bean
    @ConditionalOnMissingBean({RedisTemplate.class})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(factory);
        // 设置键的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置哈希键的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置默认的序列化器
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // 设置哈希值的序列化器
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }

    @Bean
    public RedisCache redisCache(
            RedisConnectionFactory redisConnectionFactory, MultilevelCacheProperties multilevelCacheProperties) {
        // 创建 RedisCacheWriter 实例
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 创建 RedisCacheConfiguration 实例，并设置缓存配置属性
        RedisCacheConfiguration redisCacheConfiguration = defaultCacheConfig()
                .entryTtl(Duration.of(multilevelCacheProperties.getRedisExpireTime(), ChronoUnit.SECONDS))
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()));

        // 创建 RedisCache 实例，并传入 RedisCacheWriter 和 RedisCacheConfiguration
        RedisCache redisCache = new CustomizedRedisCache(
                multilevelCacheProperties.getRedisName(), redisCacheWriter, redisCacheConfiguration);

        return redisCache;
    }

    @Bean
    @ConditionalOnClass(CaffeineCache.class)
    @ConditionalOnProperty(name = "multilevel.cache.caffeineSwitch", havingValue = "true", matchIfMissing = true)
    public CaffeineCache caffeineCache() {
        // 根据最大容量比率计算最大容量
        int maxCapacity = (int) (Runtime.getRuntime().totalMemory() * multilevelCacheProperties.getMaxCapacityRate());
        // 根据初始容量比率计算初始容量
        int initCapacity = (int) (maxCapacity * multilevelCacheProperties.getInitRate());

        // 创建缓存构建器对象
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .initialCapacity(initCapacity) // 设置初始容量
                .maximumSize(maxCapacity) // 设置最大容量
                // .executor(cacheExecutor) // 设置缓存线程池
                // .scheduler(Scheduler.systemScheduler()) // 设置定时任务执行过期清除操作
                .removalListener(new CaffeineCacheRemovalListener()) // 设置缓存移除监听器
                .expireAfterAccess(Duration.of(
                        multilevelCacheProperties.getCaffeineExpireTime(), ChronoUnit.SECONDS)) // 设置缓存读取过期时间
                .recordStats(); // 开启缓存统计信息记录

        // 创建并返回CaffeineCache对象
        CaffeineCache caffeineCache =
                new CaffeineCache(multilevelCacheProperties.getCaffeineName(), caffeineBuilder.build());
        return caffeineCache;
    }

    @Bean
    @ConditionalOnBean({CaffeineCache.class, RedisCache.class})
    public MultilevelCache multilevelCache(
            RedisCache redisCache, CaffeineCache caffeineCache, MessagePublisher redisMessagePublisher) {
        MultilevelCache multilevelCache = new MultilevelCache(
                true,
                multilevelCacheProperties.getEnableFirstCache(),
                multilevelCacheProperties.getName(),
                redisCache,
                caffeineCache,
                redisMessagePublisher);
        return multilevelCache;
    }

    @Bean
    public RedisCacheMessageListener redisCacheMessageListener(CaffeineCache caffeineCache) {
        // 创建 RedisCacheMessageListener 实例
        RedisCacheMessageListener redisCacheMessageListener = new RedisCacheMessageListener();
        // 设置 caffeineCache 依赖
        redisCacheMessageListener.setCaffeineCache(caffeineCache);
        return redisCacheMessageListener;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory, RedisCacheMessageListener redisCacheMessageListener) {
        // 创建 RedisMessageListenerContainer 实例
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        // 设置 RedisConnectionFactory 依赖
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        // 添加消息监听器和频道
        redisMessageListenerContainer.addMessageListener(
                redisCacheMessageListener, new ChannelTopic(multilevelCacheProperties.getTopic()));
        return redisMessageListenerContainer;
    }

    @Bean
    MessagePublisher redisMessagePublisher(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisMessagePublisher(redisTemplate, channelTopic());
    }

    @Bean
    ChannelTopic channelTopic() {
        return new ChannelTopic(multilevelCacheProperties.getTopic());
    }
}
