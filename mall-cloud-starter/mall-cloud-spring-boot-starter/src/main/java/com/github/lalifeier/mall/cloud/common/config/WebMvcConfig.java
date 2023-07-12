package com.github.lalifeier.mall.cloud.common.config;


import com.github.lalifeier.mall.cloud.common.converter.*;
import com.github.lalifeier.mall.cloud.common.filter.WebTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor()
//      .addPathPatterns("/**");
//  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(new IntegerToEnumConverterFactory());
    registry.addConverterFactory(new StringToEnumConverterFactory());

    registry.addConverter(new DateConverter());
    registry.addConverter(new LocalDateConverter());
    registry.addConverter(new LocalTimeConverter());
    registry.addConverter(new LocalDateTimeConverter());
  }


  @Bean
  public FilterRegistrationBean<WebTraceFilter> webTraceFilter() {
    FilterRegistrationBean<WebTraceFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new WebTraceFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}

