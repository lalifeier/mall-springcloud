package com.github.lalifeier.mall.cloud.common.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lalifeier.mall.cloud.common.annotation.IEnum;

import java.io.IOException;
import java.util.Objects;

public class IEnumSerializer extends JsonSerializer<IEnum> {

  @Override
  public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (Objects.isNull(value)) {
      gen.writeNull();
      return;
    }
    gen.writeNumber(value.getValue());
  }
}

