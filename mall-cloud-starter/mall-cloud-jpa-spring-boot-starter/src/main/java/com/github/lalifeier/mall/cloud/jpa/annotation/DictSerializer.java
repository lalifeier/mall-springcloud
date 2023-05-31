package com.github.lalifeier.mall.cloud.jpa.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Objects;

public class DictSerializer<T> extends JsonSerializer<T> implements ContextualSerializer {

  private String type;

  private Dict dict;

  public DictSerializer() {
  }


  public DictSerializer(Dict dict) {
    this.dict = dict;
  }

  @Override
  public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    if (Objects.isNull(value)) {
      gen.writeNull();
      return;
    }

    if (Objects.nonNull(dict)){
      type = dict.type();
    }

    //String label = DictEnum.changeLabel(type, value.toString());

    //gen.writeObjectField("label", label);
    //gen.writeObjectField("value", value);
    //gen.writeEndObject();
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty beanProperty) throws JsonMappingException {
    if (Objects.isNull(beanProperty)){
      return prov.findValueSerializer(beanProperty.getType(), beanProperty);
    }
    Dict dict = beanProperty.getAnnotation(Dict.class);
    if (Objects.nonNull(dict)){
      type = dict.type();
      return this;
    }

    return prov.findNullValueSerializer(null);
  }
}
