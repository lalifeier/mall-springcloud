package com.github.lalifeier.mall.cloud.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.lalifeier.mall.cloud.common.annocation.EnumValue;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

/**
 * 实用工具类，用于处理枚举类型
 */
public class EnumUtil {
  public EnumUtil() {}

  /**
   * 检查目标类型是否为枚举类型
   *
   * @param targetType 目标类型
   * @throws IllegalArgumentException 如果目标类型不是枚举类型
   */
  public static void isEnumType(Class<?> targetType) {
    if (null == targetType || !targetType.isEnum()) {
      throw new IllegalArgumentException(
          "The target type " + targetType.getName() + " does not refer to an enum");
    }
  }

  /**
   * 根据代码值获取枚举项的描述
   *
   * @param enumClass 枚举类型
   * @param code 代码值
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 枚举项的描述
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> String getDescriptionByCode(
      Class<E> enumClass, T code) {
    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> code.equals(e.getCode()))
        .findFirst().map(BaseEnum::getDescription).orElse(null);
  }

  /**
   * 根据代码值获取枚举项
   *
   * @param enumClass 枚举类型
   * @param code 代码值
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 对应的枚举项
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnumByCode(Class<E> enumClass,
      T code) {
    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> code.equals(e.getCode()))
        .findFirst().orElse(null);
  }

  /**
   * 根据名称获取枚举项
   *
   * @param enumClass 枚举类型
   * @param name 名称
   * @param <E> 枚举类型
   * @return 对应的枚举项
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnum(Class<E> enumClass, String name) {
    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> name.equals(e.name()))
        .findFirst().orElse(null);
  }

  /**
   * 根据不区分大小写的名称获取枚举项
   *
   * @param enumClass 枚举类型
   * @param name 不区分大小写的名称
   * @param <E> 枚举类型
   * @return 对应的枚举项
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnumIgnoreCase(Class<E> enumClass,
      String name) {
    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> name.equalsIgnoreCase(e.name()))
        .findFirst().orElse(null);
  }

  /**
   * 获取枚举项的代码值列表
   *
   * @param enumClass 枚举类型
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 代码值列表
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> List<T> getCodeList(Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).map(BaseEnum::getCode)
        .collect(Collectors.toList());
  }

  /**
   * 获取枚举项的描述列表
   *
   * @param enumClass 枚举类型
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 描述列表
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> List<String> getDescriptionList(
      Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).map(BaseEnum::getDescription)
        .collect(Collectors.toList());
  }

  /**
   * 获取枚举项的列表
   *
   * @param enumClass 枚举类型
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 枚举项的列表
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> List<E> getEnumList(Class<E> enumClass) {
    return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
  }

  /**
   * 将枚举转换为 Map，其中键为代码值，值为描述
   *
   * @param enumClass 枚举类型
   * @param <E> 枚举类型
   * @param <T> 代码值类型
   * @return 枚举转换后的 Map
   */
  public static <E extends Enum<E> & BaseEnum<E, T>, T> Map<T, String> getEnumMap(
      Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants())
        .collect(Collectors.toMap(BaseEnum::getCode, BaseEnum::getDescription));
  }

  public static String getEnumFieldName(Class<?> enumClass) {
    if (BaseEnum.class.isAssignableFrom(enumClass)) {
      return BaseEnum.code;
    } else {
      Field field =
          getEnumAnnotationField(enumClass).orElseThrow(() -> new IllegalArgumentException(
              String.format("Could not find @EnumValue in Class: %s.", enumClass.getName())));

      return field.getName();
    }
  }

  public static Optional<Field> getEnumAnnotationField(Class<?> enumClass) {
    return Arrays.stream(enumClass.getDeclaredFields())
        .filter(field -> field.isAnnotationPresent(EnumValue.class)).findFirst();
  }

  public static Class<?> getEnumFieldType(Class<?> enumClass, String fieldName) {
    try {
      Field field = enumClass.getDeclaredField(fieldName);
      return field.getType();
    } catch (NoSuchFieldException e) {
    }
    return null;
  }

  public static Object getEnumFieldValue(Enum<?> enumClass, String fieldName) {
    try {
      Field field = enumClass.getDeclaringClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(enumClass);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
