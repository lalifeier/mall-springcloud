package com.github.lalifeier.mall.cloud.common.converter.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

public class EnumDeserializer<T extends BaseEnum> extends JsonDeserializer<T> implements ContextualDeserializer {
  private Class<T> enumClass;

  public EnumDeserializer() {
  }

  private EnumDeserializer(Class<T> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public T deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
    //final String param = jsonParser.getText();
    //return Arrays.stream(clazz.getEnumConstants())
    //  .filter(x -> x.getCode().toString().equals(param))
    //  .findFirst()
    //  .orElse(null);

    return null;
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
    JavaType type = deserializationContext.getContextualType();
    Class<T> enumClass = (Class<T>) type.getRawClass();
    return new EnumDeserializer(enumClass);
  }
}
