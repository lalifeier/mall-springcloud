package com.github.lalifeier.mall.cloud.common.factory;

import com.github.lalifeier.mall.cloud.common.annotation.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class IEnumConverterFactory implements ConverterFactory<String, IEnum> {
  @Override
  public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
    return source ->
      Arrays.stream(targetType.getEnumConstants())
        .filter(x -> x.getValue().toString().equals(source))
        .findFirst()
        .orElse(null);
  }
}
