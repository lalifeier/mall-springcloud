package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    static class StringToEnumConverter<T extends Enum<?>> implements Converter<String, T> {
        private final Class<T> enumClass;
        private final Map<String, T> enumConstantMap;

        StringToEnumConverter(Class<T> enumClass) {
            this.enumClass = enumClass;

            String fieldName = EnumUtil.getEnumFieldName(enumClass);

            this.enumConstantMap = new ConcurrentHashMap<>();
            T[] enumConstants = enumClass.getEnumConstants();
            for (T enumConstant : enumConstants) {
                Object fieldValue = EnumUtil.getEnumFieldValue(enumConstant, fieldName);
                if (fieldValue != null) {
                    enumConstantMap.put(fieldValue.toString(), enumConstant);
                }
            }
        }

        @Override
        public T convert(String source) {
            T enumValue = enumConstantMap.get(source);
            if (enumValue == null) {
                log.error("StringToEnumConverter: invalid enum code " + source);
                return null;
            }
            return enumValue;
        }
    }
}
