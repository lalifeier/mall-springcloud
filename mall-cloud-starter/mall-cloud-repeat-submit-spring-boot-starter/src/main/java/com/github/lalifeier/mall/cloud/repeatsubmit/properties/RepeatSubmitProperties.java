package com.github.lalifeier.mall.cloud.repeatsubmit.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.repeat-submit")
public class RepeatSubmitProperties {
  private Boolean enable;
}
