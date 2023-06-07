package com.github.lalifeier.mall.cloud.common.converter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

import java.io.IOException;
import java.util.Objects;

public class EnumSerializer extends JsonSerializer<BaseEnum<String>> {

  @Override
  public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (Objects.isNull(value)) {
      gen.writeNull();
      return;
    }
    gen.writeStartObject();
    gen.writeObjectField("code", value.getCode());
    gen.writeObjectField("message", value.getMessage());
    gen.writeEndObject();
  }
}

