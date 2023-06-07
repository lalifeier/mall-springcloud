package com.github.lalifeier.mall.cloud.common.factory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.annotation.IEnum;
import lombok.SneakyThrows;

import java.util.Arrays;

public class IEnumDeserializer extends JsonDeserializer<IEnum> implements ContextualDeserializer {
  private Class<? extends IEnum> clazz;

  public IEnumDeserializer() {
  }

  public IEnumDeserializer(Class<? extends IEnum> clazz) {
    this.clazz = clazz;
  }

  @SneakyThrows
  @Override
  public IEnum deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
    final String param = jsonParser.getText();
    return Arrays.stream(clazz.getEnumConstants())
      .filter(x -> x.getValue().toString().equals(param))
      .findFirst()
      .orElse(null);
  }

  @SuppressWarnings({"unchecked"})
  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    JavaType type = property.getType();
    // 如果是容器，则返回容器内部枚举类型
    while (type.isContainerType()) {
      type = type.getContentType();
    }
    return new IEnumDeserializer((Class<? extends IEnum>) type.getRawClass());
  }
}
