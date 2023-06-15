package com.github.lalifeier.mall.cloud.ratelimit.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = RateLimitProperties.PREFIX)
public class RateLimitProperties {

  public static final String PREFIX = "spring.rate-limit";

  private Boolean enable;

  private int statusCode = 429;
  private String message = "Too Many Requests";
}
