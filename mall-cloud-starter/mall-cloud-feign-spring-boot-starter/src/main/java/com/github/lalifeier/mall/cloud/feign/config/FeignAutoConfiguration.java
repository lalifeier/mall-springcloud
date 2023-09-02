package com.github.lalifeier.mall.cloud.feign.config;

import com.github.lalifeier.mall.cloud.feign.codec.FeignDecoder;
import com.github.lalifeier.mall.cloud.feign.codec.FeignErrorDecoder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignAutoConfiguration {

  /**
   * NONE：默认的，不显示任何日志 BASIC：仅记录请求方法、URL、响应状态码及执行时间 HEADERS：出了BASIC中定义的信息之外，还有请求和响应的头信息
   * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元素
   */
  @Bean
  public Logger.Level feginLoggerLevel() {
    return Logger.Level.NONE;
  }

  // @Bean
  // public Retryer feignRetryer() {
  // return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5);
  // }

  public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
    final HttpMessageConverters httpMessageConverters =
        new HttpMessageConverters(new PhpMappingJackson2HttpMessageConverter());
    return () -> httpMessageConverters;
  }

  public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    PhpMappingJackson2HttpMessageConverter() {
      List<MediaType> mediaTypes = new ArrayList<>();
      mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON + ";charset=UTF-8"));
      setSupportedMediaTypes(mediaTypes);
    }
  }

  @Bean
  @ConditionalOnMissingBean
  public Decoder feignDecoder() {
    return new FeignDecoder(new SpringDecoder(feignHttpMessageConverter()));
  }

  @Bean
  public ErrorDecoder feignErrorDecoder() {
    return new FeignErrorDecoder();
  }
}
