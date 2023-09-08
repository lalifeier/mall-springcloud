package com.github.lalifeier.mall.cloud.common.converter.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;

import java.io.IOException;

public class EnumDeserializer<E extends Enum<E> & BaseEnum<E, T>, T> extends JsonDeserializer<E>
        implements ContextualDeserializer {

    public static final EnumDeserializer INSTANCE = new EnumDeserializer();

    private Class<E> enumClass;

    public EnumDeserializer() {}

    private EnumDeserializer(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();

        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            return BaseEnum.parse(enumClass, (T) value);
        } else {
            return Enum.valueOf(enumClass, value);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(
            DeserializationContext deserializationContext, BeanProperty beanProperty) {
        Class<?> enumType = deserializationContext.getContextualType().getRawClass();
        return new EnumDeserializer<>((Class<E>) enumType);
    }
}
