package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.component.DictSensitiveAnnotationIntrospector;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DictSerializerConfig {

  @Resource
  private DictSensitiveAnnotationIntrospector dictSensitiveAnnotationIntrospector;

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.annotationIntrospector(dictSensitiveAnnotationIntrospector);
  }
}
