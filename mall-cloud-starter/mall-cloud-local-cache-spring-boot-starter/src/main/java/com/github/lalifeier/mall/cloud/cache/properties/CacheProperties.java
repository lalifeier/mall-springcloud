package com.github.lalifeier.mall.cloud.cache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "local.cache")
public class CacheProperties {
  private Integer expireAfterWrite = 300;
  private Integer maximumSize = 1024;
}
