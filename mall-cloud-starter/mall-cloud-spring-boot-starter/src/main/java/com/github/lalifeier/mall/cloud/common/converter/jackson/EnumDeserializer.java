package com.github.lalifeier.mall.cloud.common.converter.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import java.io.IOException;

public class EnumDeserializer<T extends BaseEnum> extends JsonDeserializer<T>
    implements ContextualDeserializer {

  private Class<T> enumClass;

  public EnumDeserializer() {}

  private EnumDeserializer(Class<T> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    String code = jsonParser.getText();
    for (T constant : enumClass.getEnumConstants()) {
      if (constant.getCode().equals(code)) {
        return constant;
      }
    }
    throw new IllegalArgumentException("Unknown enum code: " + code);
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext,
      BeanProperty beanProperty) throws JsonMappingException {
    return new EnumDeserializer(beanProperty.getType().getRawClass());
  }
}
