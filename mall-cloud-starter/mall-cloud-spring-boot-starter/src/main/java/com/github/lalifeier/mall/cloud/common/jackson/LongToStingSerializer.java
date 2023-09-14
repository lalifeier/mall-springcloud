package com.github.lalifeier.mall.cloud.common.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LongToStingSerializer extends JsonSerializer<Long> {

  public static final LongToStingSerializer INSTANCE = new LongToStingSerializer();

  @Override
  public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (value == null) {
      gen.writeString("");
    } else {
      gen.writeString(String.valueOf(value));
    }
  }
}
