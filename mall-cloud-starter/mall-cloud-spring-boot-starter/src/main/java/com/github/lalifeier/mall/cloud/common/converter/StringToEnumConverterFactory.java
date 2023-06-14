package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StringToEnumConverterFactory implements ConverterFactory<String, BaseEnum<String>> {
  private final ConcurrentMap<Class<? extends BaseEnum<String>>, StringToEnumConverter<?>> CONVERTERS =
    new ConcurrentHashMap<>();

  @Override
  public <T extends BaseEnum<String>> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
    @SuppressWarnings("unchecked")
    StringToEnumConverter<T> converter = (StringToEnumConverter<T>) CONVERTERS.get(targetType);
    if (converter == null) {
      converter = new StringToEnumConverter<>(targetType);
      CONVERTERS.put(targetType, converter);
    }
    return converter;
  }
}