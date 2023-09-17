package com.github.lalifeier.mall.cloud.common.jackson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import com.github.lalifeier.mall.cloud.common.utils.EnumUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumSerializer extends JsonSerializer<Enum> {
  public static String LABEL_FIELD_NAME = "label";
  public static String VALUE_FIELD_NAME = "value";

  public static final EnumSerializer INSTANCE = new EnumSerializer();

  @Override
  public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {

    if (Objects.isNull(value)) {
      gen.writeNull();
      return;
    }

    if (BaseEnum.class.isAssignableFrom(value.getClass())) {
      BaseEnum<?, ?> baseEnum = (BaseEnum<?, ?>) value;

      gen.writeStartObject();
      gen.writeObjectField(LABEL_FIELD_NAME, baseEnum.getCode());
      gen.writeObjectField(VALUE_FIELD_NAME, baseEnum.getDescription());
      gen.writeEndObject();

      return;
    }

    Optional<Field> field = EnumUtil.getAnnotationField(value.getClass());
    if (field.isPresent()) {
      try {
        field.get().setAccessible(true);
        Object fieldValue = field.get().get(value);

        gen.writeObject(fieldValue);
      } catch (Exception e) {
        log.error("Failed to serialize enum value", e);
      }
      return;
    }

    gen.writeString(value.name());
  }
}
