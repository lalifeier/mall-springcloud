package com.github.lalifeier.mall.cloud.demo.infrastructure.config;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("com.github.lalifeier.mall.demo.**.feign")
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignConfig {
  //
  // @Bean
  // public OkHttpClient okHttpClient(OkhttpProperties okhttpProperties) {
  // return new OkHttpClient.Builder()
  // .connectTimeout(okhttpProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
  // .readTimeout(okhttpProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
  // .writeTimeout(okhttpProperties.getWriteTimeout(), TimeUnit.MILLISECONDS)
  // .retryOnConnectionFailure(true)
  // .connectionPool(new ConnectionPool())
  // .build();
  // }

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder().connectionPool(new ConnectionPool()).build();
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.BASIC;
  }

  @Bean
  public Retryer feignRetry() {
    return new Retryer.Default();
  }
}
