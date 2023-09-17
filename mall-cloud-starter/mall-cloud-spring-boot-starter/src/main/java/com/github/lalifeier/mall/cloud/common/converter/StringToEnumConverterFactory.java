package com.github.lalifeier.mall.cloud.common.converter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

@Slf4j
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum<?>> {

    private static final Map<Class<? extends Enum<?>>, Converter<String, ? extends Enum<?>>> CONVERTERS =
            Collections.synchronizedMap(new ConcurrentHashMap<>());

    @Override
    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
        @SuppressWarnings("unchecked")
        Converter<String, T> converter = (Converter<String, T>)
                CONVERTERS.computeIfAbsent(targetType, key -> new StringToEnumConverter<>(targetType));
        return converter;
    }

    static class StringToEnumConverter<T extends Enum<?>> implements Converter<String, Enum<?>> {
        private final Class<T> enumClass;

        StringToEnumConverter(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public Enum<?> convert(String source) {
            if (source == null) {
                return null;
            }
            return EnumConverter.convert(enumClass, source);
        }
    }
}
