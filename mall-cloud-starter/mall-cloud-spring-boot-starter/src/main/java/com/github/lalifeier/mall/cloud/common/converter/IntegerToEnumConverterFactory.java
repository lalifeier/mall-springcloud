package com.github.lalifeier.mall.cloud.common.converter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, Enum<?>> {

    private static final Map<Class<? extends Enum<?>>, Converter<Integer, ? extends Enum<?>>> CONVERTERS =
            Collections.synchronizedMap(new ConcurrentHashMap<>());

    @Override
    public <T extends Enum<?>> Converter<Integer, T> getConverter(Class<T> targetType) {
        @SuppressWarnings("unchecked")
        Converter<Integer, T> converter = (Converter<Integer, T>)
                CONVERTERS.computeIfAbsent(targetType, key -> new IntegerToEnumConverter<>(targetType));
        return converter;
    }

    static class IntegerToEnumConverter<T extends Enum<?>> implements Converter<Integer, Enum<?>> {
        private final Class<T> enumClass;

        IntegerToEnumConverter(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public Enum<?> convert(Integer source) {
            if (source == null) {
                return null;
            }
            return EnumConverter.convert(enumClass, source);
        }
    }
}
