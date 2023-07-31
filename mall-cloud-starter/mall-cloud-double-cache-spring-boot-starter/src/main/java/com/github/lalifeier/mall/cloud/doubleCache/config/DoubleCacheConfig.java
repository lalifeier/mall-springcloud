package com.github.lalifeier.mall.cloud.doubleCache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "doublecache")
public class DoubleCacheConfig {
    private Boolean allowNull = true;
    private Integer init = 128;
    private Integer max = 1024;
    private Long expireAfterWrite;
    private Long expireAfterAccess;
    private Long refreshAfterWrite;
    private Long redisExpire;
}
