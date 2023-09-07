package com.github.lalifeier.mall.cloud.common.enums;

import java.util.Objects;

// @JsonDeserialize(using = EnumDeserializer.class)
public interface BaseEnum<E extends Enum<E>, T> {

  /**
   * 获取枚举标识
   *
   * @return
   */
  T getCode();

  /**
   * 获取枚举描述
   *
   * @return
   */
  String getDescription();

  /**
   * 通过枚举类型和code值获取对应的枚举类型
   *
   * @param clazz
   * @param code
   * @return
   */
  static <E extends Enum<E> & BaseEnum<E, T>, T> E parse(Class<E> clazz, T code) {
    if (null == code || "".equals(code)) {
      return null;
    }

    for (E e : clazz.getEnumConstants()) {
      if (Objects.equals(e.getCode(), code))
        return e;
    }

    return null;
  }

  // static <E extends Enum<E> & BaseEnum<E, T>, T> List<T> getEnumList(Class<E> clazz) {
  // E[] enumConstants = clazz.getEnumConstants();
  // return Arrays.asList(enumConstants).stream().map(E::getCode).collect(Collectors.toList());
  // }
}
