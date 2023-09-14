package com.github.lalifeier.mall.cloud.common.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class StringToLongDeserializer extends JsonDeserializer<Long> implements ContextualDeserializer {

    private Class<?> clazz;

    public StringToLongDeserializer() {}

    private StringToLongDeserializer(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String value = jsonParser.getValueAsString();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty property)
            throws JsonMappingException {
        Class<?> rawClazz = deserializationContext.getContextualType().getRawClass();
        return new StringToLongDeserializer(rawClazz);
    }
}
