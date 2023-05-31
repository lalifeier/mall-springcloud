package com.github.lalifeier.mall.cloud.mybatisplus.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TypeConvertUtils {

  private static final Map<Class<?>, Function<String, Object>> CONVERTERS = new HashMap<>();

  static {
    CONVERTERS.put(Integer.class, Integer::valueOf);
    CONVERTERS.put(Long.class, Long::valueOf);
    CONVERTERS.put(Float.class, Float::valueOf);
    CONVERTERS.put(Double.class, Double::valueOf);
    CONVERTERS.put(Boolean.class, Boolean::valueOf);
    CONVERTERS.put(String.class, s -> s);
    CONVERTERS.put(LocalDate.class, s -> LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE));
    CONVERTERS.put(LocalDateTime.class, s -> LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    CONVERTERS.put(LocalTime.class, s -> LocalTime.parse(s, DateTimeFormatter.ISO_LOCAL_TIME));
    // 添加其他类型转换方法
  }

  /**
   * 将字符串类型的值转换为实际类型
   *
   * @param value 字符串类型的值
   * @param type  实际类型
   * @return 实际类型的值
   */
  public static Object convertStringToType(String value, Class<?> type) {
    Function<String, Object> converter = CONVERTERS.get(type);
    if (converter != null) {
      return converter.apply(value);
    }
    throw new IllegalArgumentException("Unsupported type: " + type.getName());
  }

  // 添加其他类型转换方法
}
