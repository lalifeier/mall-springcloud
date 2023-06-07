package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum<Integer>> {
  private final ConcurrentMap<Class<? extends BaseEnum<Integer>>, IntegerToEnumConverter<?>> CONVERTERS =
    new ConcurrentHashMap<>();

  @Override
  public <T extends BaseEnum<Integer>> Converter<Integer, T> getConverter(@NotNull Class<T> targetType) {
    @SuppressWarnings("unchecked")
    IntegerToEnumConverter<T> converter = (IntegerToEnumConverter<T>) CONVERTERS.get(targetType);
    if (converter == null) {
      converter = new IntegerToEnumConverter<>(targetType);
      CONVERTERS.put(targetType, converter);
    }
    return converter;
  }
}
