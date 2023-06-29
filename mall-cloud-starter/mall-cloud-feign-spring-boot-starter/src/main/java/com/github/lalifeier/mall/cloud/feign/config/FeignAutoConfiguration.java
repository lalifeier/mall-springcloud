package com.github.lalifeier.mall.cloud.feign.config;

import com.github.lalifeier.mall.cloud.feign.component.FeignDecoder;
import com.github.lalifeier.mall.cloud.feign.component.FeignErrorDecoder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignAutoConfiguration {

  /**
   * NONE：默认的，不显示任何日志
   * BASIC：仅记录请求方法、URL、响应状态码及执行时间
   * HEADERS：出了BASIC中定义的信息之外，还有请求和响应的头信息
   * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元素
   */
  @Bean
  public Logger.Level feginLoggerLevel() {
    return Logger.Level.NONE;
  }

  @Bean
  public Decoder feignDecode() {
    return new FeignDecoder();
  }


  @Bean
  public ErrorDecoder feignErrorDecoder() {
    return new FeignErrorDecoder();
  }
}
