package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.factory.IEnumConverterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final IEnumConverterFactory iEnumConverterFactory;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(iEnumConverterFactory);
  }
}

