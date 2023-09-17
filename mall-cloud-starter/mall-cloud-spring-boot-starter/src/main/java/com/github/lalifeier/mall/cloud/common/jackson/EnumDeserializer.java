package com.github.lalifeier.mall.cloud.common.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.converter.EnumConverter;
import java.io.IOException;

public class EnumDeserializer extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {

    public static final EnumDeserializer INSTANCE = new EnumDeserializer();
    private Class<? extends Enum<?>> enumClass;

    public EnumDeserializer() {}

    private EnumDeserializer(Class<? extends Enum<?>> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();
        return EnumConverter.convert(enumClass, value);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty property)
            throws JsonMappingException {
        Class<?> enumType = deserializationContext.getContextualType().getRawClass();
        return new EnumDeserializer((Class<? extends Enum<?>>) enumType);
    }
}
