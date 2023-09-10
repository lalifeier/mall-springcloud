package com.github.lalifeier.mall.cloud.common.jackson;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

  public static final BaseEnumSerializer INSTANCE = new BaseEnumSerializer();

  @Override
  public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (Objects.isNull(value)) {
      gen.writeNull();
      return;
    }

    gen.writeStartObject();
    gen.writeObjectField(BaseEnum.code, value.getCode());
    gen.writeObjectField(BaseEnum.description, value.getDescription());
    gen.writeEndObject();
  }
}
