package com.github.lalifeier.mall.cloud.common.converter;


import java.lang.reflect.ParameterizedType;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

import jakarta.persistence.AttributeConverter;

public abstract class BaseEnumConverter<E extends Enum<E> & BaseEnum<E, T>, T>
    implements AttributeConverter<E, T> {

  @Override
  public T convertToDatabaseColumn(E attribute) {
    return attribute.getCode();
  }

  @Override
  public E convertToEntityAttribute(T dbData) {
    Class<E> enumClz = (Class<E>) (((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments())[0];
    return BaseEnum.parse(enumClz, dbData);
  }
}
