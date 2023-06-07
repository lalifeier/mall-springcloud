package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.converter.IntegerToEnumConverterFactory;
import com.github.lalifeier.mall.cloud.common.converter.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(new IntegerToEnumConverterFactory());
    registry.addConverterFactory(new StringToEnumConverterFactory());
  }
}

