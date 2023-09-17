package com.github.lalifeier.mall.cloud.jpa.converter;

import java.lang.reflect.ParameterizedType;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

import jakarta.persistence.AttributeConverter;

/**
 * 抽象类 BaseEnumConverter 是一个通用的枚举转换器，用于将实现了 BaseEnum 接口的枚举类型转换为数据库列类型，并在需要时将数据库列类型转换回枚举类型。
 *
 * @param <E> 枚举的类型
 * @param <T> 数据库列类型
 */
public abstract class BaseEnumConverter<E extends Enum<E> & BaseEnum<E, T>, T>
    implements AttributeConverter<E, T> {

  /**
   * 将枚举类型的 attribute 转换为数据库列类型。
   *
   * @param attribute 枚举类型的 attribute
   * @return 数据库列类型
   */
  @Override
  public T convertToDatabaseColumn(E attribute) {
    if (attribute == null) {
      return null;
    }
    return attribute.getCode();
  }

  /**
   * 将数据库列类型的 dbData 转换为枚举类型。
   *
   * @param dbData 数据库列类型的 dbData
   * @return 枚举类型
   */
  @Override
  public E convertToEntityAttribute(T dbData) {
    if (dbData == null) {
      return null;
    }
    Class<E> enumClz = getEnumClass();
    return BaseEnum.valueOf(enumClz, dbData);
  }

  /**
   * 获取泛型参数 E 的实际枚举类型。
   *
   * @return 泛型参数 E 的实际枚举类型
   */
  @SuppressWarnings("unchecked")
  private Class<E> getEnumClass() {
    ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<E>) parameterizedType.getActualTypeArguments()[0];
  }
}
