package com.github.lalifeier.mall.cloud.common.converter;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public abstract class BaseDateConverter<T> {
  public T convert(String source, Function<String, T> function) {
    if (source == null || source.isEmpty()) {
      return null;
    }

    source = source.trim();
    Set<Map.Entry<String, String>> entries = getFormat().entrySet();
    for (Map.Entry<String, String> entry : entries) {
      if (source.matches(entry.getValue())) {
        return function.apply(entry.getKey());
      }
    }

    throw new IllegalArgumentException("invalid time format:'" + source + "'");
  }

  protected abstract Map<String, String> getFormat();
}

