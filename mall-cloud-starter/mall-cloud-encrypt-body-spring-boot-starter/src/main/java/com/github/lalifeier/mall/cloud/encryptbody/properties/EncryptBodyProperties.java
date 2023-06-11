package com.github.lalifeier.mall.cloud.encryptbody.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "encrypt.body")
public class EncryptBodyProperties {
  private Boolean enable;
  private String publicKey;
  private String privateKey;
  private String encoding = "UTF-8";
}
