package com.github.lalifeier.mall.cloud.encryptbody.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptBodyConfig {

  private Boolean enable;
  private String publicKey;
  private String privateKey;
  private String encoding;
}
