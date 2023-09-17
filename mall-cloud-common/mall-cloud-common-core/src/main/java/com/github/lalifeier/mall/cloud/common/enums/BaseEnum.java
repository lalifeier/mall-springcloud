package com.github.lalifeier.mall.cloud.common.enums;

import java.util.Arrays;

import com.github.lalifeier.mall.cloud.common.utils.reflect.ClassUtil;

public interface BaseEnum<E extends Enum<E>, T> {

  // @JsonValue
  T getCode();

  String getDescription();

  static <E extends Enum<E> & BaseEnum<E, T>, T> E valueOf(Class<E> enumClass, Object enumValue) {
    if (!ClassUtil.isEnum(enumClass)) {
      return null;
    }
    if (enumValue == null || enumValue.toString().isEmpty()) {
      return null;
    }

    return Arrays.stream(enumClass.getEnumConstants())
        .filter(e -> String.valueOf(e.getCode()).equalsIgnoreCase(String.valueOf(enumValue)))
        .findFirst().orElse(null);
  }
}
