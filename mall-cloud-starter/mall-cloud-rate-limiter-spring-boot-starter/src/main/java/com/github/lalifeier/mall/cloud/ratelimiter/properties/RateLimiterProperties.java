package com.github.lalifeier.mall.cloud.ratelimiter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = RateLimiterProperties.PREFIX)
public class RateLimiterProperties {
  public static final String PREFIX = "spring.rate-limiter";

  private Boolean enable;
  // private int statusCode = 429;
  // private String message = "Too Many Requests";
}
