package com.github.lalifeier.mall.cloud.common.jackson;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

public class BaseEnumDeserializer<E extends Enum<E> & BaseEnum<E, T>, T> extends JsonDeserializer<E>
    implements ContextualDeserializer {

  public static final BaseEnumDeserializer INSTANCE = new BaseEnumDeserializer();
  private Class<E> enumClass;

  public BaseEnumDeserializer() {}

  private BaseEnumDeserializer(Class<E> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {

    String value = jsonParser.getValueAsString();

    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> value.equals(e.getCode()))
        .findFirst().orElse(null);
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext,
      BeanProperty beanProperty) {
    Class<?> enumType = deserializationContext.getContextualType().getRawClass();
    return new BaseEnumDeserializer<>((Class<E>) enumType);
  }
}


