package com.github.lalifeier.mall.cloud.trace.config;

import com.github.lalifeier.mall.cloud.trace.filter.WebTraceFilter;
import com.github.lalifeier.mall.cloud.trace.interceptor.FeignTraceInterceptor;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TraceProperties.class)
public class TraceAutoConfiguration {

  private final TraceProperties properties;

  public TraceAutoConfiguration(TraceProperties properties) {
    this.properties = properties;
  }

  @Bean
  public FilterRegistrationBean webTraceFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean<>();
    registration.setFilter(new WebTraceFilter());
    registration.addUrlPatterns("/*");
    return registration;
  }

  @Bean
  public FeignTraceInterceptor feignTraceInterceptor() {
    return new FeignTraceInterceptor();
  }
}
