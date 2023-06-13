package com.github.lalifeier.mall.cloud.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "encrypt.body")
public class EncryptBodyProperties {
}
