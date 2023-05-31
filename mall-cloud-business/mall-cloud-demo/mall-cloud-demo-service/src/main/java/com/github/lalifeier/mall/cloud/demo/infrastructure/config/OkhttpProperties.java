package com.github.lalifeier.mall.cloud.demo.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "feign.okhttp")
public class OkhttpProperties {
    private Long connectTimeout;
    private Long readTimeout;
    private Long writeTimeout;
}

