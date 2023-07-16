package com.github.lalifeier.mall.cloud.trace.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.trace")
public class TraceProperties {

  /**
   * 是否开启日志链路追踪
   */
  private Boolean enable = true;
}
