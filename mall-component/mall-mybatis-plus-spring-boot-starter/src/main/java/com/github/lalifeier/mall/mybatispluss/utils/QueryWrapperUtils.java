package com.github.lalifeier.mall.mybatispluss.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class QueryWrapperUtils {

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
  }

  /**
   * 根据提供的过滤器和排序选项构建 QueryWrapper 对象。
   *
   * @param wrapper   要添加过滤器和排序选项的 QueryWrapper 对象。
   * @param filter    要应用于查询的过滤器映射。
   * @param clazz     要查询的类。
   * @param sortField 要按其对查询结果进行排序的字段。
   * @param sortOrder 要对查询结果排序的顺序。
   */
  public static void buildQueryWrapper(QueryWrapper<?> wrapper, Map<String, String> filter, Class<?> clazz, String sortField, String sortOrder) {
    addSortToWrapper(wrapper, sortField, sortOrder);

    if (filter == null || filter.isEmpty()) {
      return;
    }

    for (Map.Entry<String, String> entry : filter.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      String[] parts = key.split("_");
      String fieldName = parts[0];
      String operator = parts.length > 1 ? parts[1] : "eq";
      Field field = ReflectionUtils.findField(clazz, fieldName);
      if (field == null) {
        continue;
      }

      Class<?> fieldType = field.getType();
      if (operator.equals("range")) {
        if (!key.endsWith("_range")) {
          continue;
        }
        String[] range = value.split(",");
        if (range.length != 2) {
          continue;
        }
        Object start = convertStringToType(range[0], fieldType);
        Object end = convertStringToType(range[1], fieldType);
        wrapper.between(fieldName, start, end);
      } else {
        Object typedValue = convertStringToType(value, fieldType);
        Method method = ReflectionUtils.findMethod(QueryWrapper.class, operator, boolean.class, Object.class, Object.class);
        if (method != null) {
          ReflectionUtils.invokeMethod(method, wrapper, true, fieldName, typedValue);
        }
      }
    }
  }

  /**
   * 将排序选项添加到提供的 QueryWrapper 对象中。
   *
   * @param wrapper   要添加排序选项的 QueryWrapper 对象。
   * @param sortField 要按其对查询结果进行排序的字段。
   * @param sortOrder 要对查询结果排序的顺序。
   */
  public static void addSortToWrapper(QueryWrapper<?> wrapper, String sortField, String sortOrder) {
    if (sortField == null || sortField.isEmpty()) {
      return;
    }

    if (sortOrder == null || sortOrder.isEmpty() || sortOrder.equalsIgnoreCase("asc")) {
      wrapper.orderByAsc(sortField);
    } else {
      wrapper.orderByDesc(sortField);
    }
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
}

