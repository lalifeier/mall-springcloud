package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringToEnumConverter<T extends BaseEnum<String>> implements Converter<String, T> {

  private final Map<String, T> enumConstantMap = new ConcurrentHashMap<>();

  public StringToEnumConverter(Class<T> enumType) {
    T[] enumConstants = enumType.getEnumConstants();
    for (T enumConstant : enumConstants) {
      this.enumConstantMap.put(enumConstant.getCode(), enumConstant);
    }
  }

  @Override
  public T convert(@NotNull String source) {
    return enumConstantMap.get(source);
  }
}
