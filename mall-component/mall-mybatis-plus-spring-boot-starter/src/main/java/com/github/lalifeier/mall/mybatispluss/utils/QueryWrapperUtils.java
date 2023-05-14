package com.github.lalifeier.mall.mybatispluss.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.lalifeier.mall.common.model.PageRequest;

public class QueryWrapperUtils {

  /**
   * 根据提供的过滤器和排序选项构建 QueryWrapper 对象。
   *
   * @param clazz   要查询的类。
   * @param request 包含过滤器、排序选项和要选择的列的请求对象。
   * @param <T>     查询结果的类型。
   * @return 构建的 QueryWrapper 对象。
   */
  public static <T> QueryWrapper<T> buildQueryWrapper(Class<?> clazz, PageRequest request) {
    QueryWrapper<T> queryWrapper = new QueryWrapper<>();

    addColumnsToWrapper(queryWrapper, clazz, request.getColumns());

    addFiltersToWrapper(queryWrapper, clazz, request.getFilter());

    addSortToWrapper(queryWrapper, clazz, request.getOrderBy());

    return queryWrapper;
  }

  /**
   * 将要选择的列添加到提供的 QueryWrapper 对象中。
   *
   * @param wrapper 要添加要选择的列的 QueryWrapper 对象。
   * @param clazz   要查询的类。
   * @param columns 要选择的列的名称。
   */
  private static void addColumnsToWrapper(QueryWrapper<?> wrapper, Class<?> clazz, String[] columns) {
    // 如果 columns 参数为空，则直接返回。
    if (ArrayUtils.isEmpty(columns)) {
      return;
    }

    // 使用 Stream API 筛选出有效的列名称，并添加到 QueryWrapper 对象中。
    Stream.of(columns)
        .filter(StringUtils::isNotBlank) // 过滤掉空白列。
        .map(fieldName -> ReflectionUtils.findField(clazz, fieldName)) // 查找类中的字段。
        .filter(Objects::nonNull) // 过滤掉无效的字段。
        .map(Field::getName) // 获取字段名称。
        .forEach(wrapper::select); // 添加到 QueryWrapper 对象中。
  }

  /**
   * 将过滤条件添加到提供的 QueryWrapper 对象中。
   *
   * @param wrapper 要添加过滤条件的 QueryWrapper 对象。
   * @param clazz   要查询的类。
   * @param filter  过滤条件，键值对的形式。
   */
  private static void addFiltersToWrapper(QueryWrapper<?> wrapper, Class<?> clazz, Map<String, String> filter) {
    // 如果 filter 参数为空，则直接返回。
    if (MapUtils.isEmpty(filter)) {
      return;
    }

    // 使用 Stream API 筛选出有效的过滤条件，并添加到 QueryWrapper 对象中。
    filter.entrySet().stream()
        .filter(entry -> StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) // 过滤掉空白条件。
        .forEach(entry -> addFilterToWrapper(wrapper, clazz, entry.getKey(), entry.getValue())); // 添加到 QueryWrapper
                                                                                                 // 对象中。
  }

  /**
   * 将单个过滤条件添加到提供的 QueryWrapper 对象中。
   *
   * @param wrapper 要添加过滤条件的 QueryWrapper 对象。
   * @param clazz   要查询的类。
   * @param key     过滤条件的键。
   * @param value   过滤条件的值。
   */
  private static void addFilterToWrapper(QueryWrapper<?> wrapper, Class<?> clazz, String key, String value) {
    String[] parts = key.split("_");
    String fieldName = parts[0];
    String operator = parts.length > 1 ? parts[1] : "eq";
    Field field = ReflectionUtils.findField(clazz, fieldName);
    if (field == null) {
      return; // 如果指定的字段不存在，则直接返回。
    }

    Class<?> fieldType = field.getType();
    if ("range".equals(operator) && key.endsWith("_range")) {
      String[] range = value.split(",");
      if (range.length == 2) {
        Object start = TypeConvertUtils.convertStringToType(range[0], fieldType);
        Object end = TypeConvertUtils.convertStringToType(range[1], fieldType);
        wrapper.between(fieldName, start, end); // 如果是范围查询，则使用 between 方法。
      }
    } else {
      Object typedValue = TypeConvertUtils.convertStringToType(value, fieldType);
      Method method = ReflectionUtils.findMethod(QueryWrapper.class, operator, boolean.class, Object.class,
          Object.class);
      if (method != null) {
        ReflectionUtils.invokeMethod(method, wrapper, true, fieldName, typedValue); // 否则，使用对应的方法。
      }
    }
  }

  /**
   * 将排序选项添加到提供的 QueryWrapper 对象中。
   *
   * @param wrapper 要添加排序选项的 QueryWrapper 对象。
   * @param clazz   要查询的类。
   * @param orderBy 排序选项，按逗号分隔的字段列表。每个字段可以有可选的 "-" 前缀，表示按降序排序。
   */
  public static void addSortToWrapper(QueryWrapper<?> wrapper, Class<?> clazz, String orderBy) {
    // 如果 orderBy 参数为空或空格，则直接返回。
    if (StringUtils.isBlank(orderBy)) {
      return;
    }

    // 按逗号分隔排序选项，构造排序条件
    List<Function<QueryWrapper<?>, QueryWrapper<?>>> orderBys = Stream.of(orderBy.split(","))
        .map(order -> {
          boolean isAsc = !order.startsWith("-");
          String columnName = isAsc ? order : order.substring(1);
          Field field = ReflectionUtils.findField(clazz, columnName);
          if (field != null) {
            return (Function<QueryWrapper<?>, QueryWrapper<?>>) (wrapper1 -> wrapper1.orderBy(true, isAsc,
                field.getName()));
          }
          return null;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    // 将所有排序条件应用到查询条件构造器中
    orderBys.forEach(orderByFunc -> orderByFunc.apply(wrapper));
  }

}
