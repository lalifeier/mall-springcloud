package com.github.lalifeier.mall.cloud.encryptbody.config;

import com.github.lalifeier.mall.cloud.encryptbody.advice.DecryptRequestBodyAdvice;
import com.github.lalifeier.mall.cloud.encryptbody.advice.EncryptResponseBodyAdvice;
import com.github.lalifeier.mall.cloud.encryptbody.properties.EncryptBodyProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EncryptBodyProperties.class)
@ConditionalOnClass({DecryptRequestBodyAdvice.class, EncryptResponseBodyAdvice.class})
public class EncryptBodyAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public EncryptBodyConfig encryptBodyConfig(EncryptBodyProperties properties) {
    return new EncryptBodyConfig(properties.getEnable(), properties.getPublicKey(), properties.getPrivateKey(), properties.getEncoding());
  }

  @Bean
  @ConditionalOnMissingBean
  public EncryptResponseBodyAdvice encryptResponseBodyAdvice(EncryptBodyConfig encryptBodyConfig) {
    return new EncryptResponseBodyAdvice(encryptBodyConfig);
  }

  @Bean
  @ConditionalOnMissingBean
  public DecryptRequestBodyAdvice decryptRequestBodyAdvice(EncryptBodyConfig encryptBodyConfig) {
    return new DecryptRequestBodyAdvice(encryptBodyConfig);
  }
}
