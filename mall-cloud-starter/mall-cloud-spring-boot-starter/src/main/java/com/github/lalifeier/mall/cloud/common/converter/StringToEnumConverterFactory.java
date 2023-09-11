package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.annocation.EnumValue;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

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

            String fieldName = getEnumFieldName(enumClass);

            this.enumConstantMap = new ConcurrentHashMap<>();
            T[] enumConstants = enumClass.getEnumConstants();
            for (T enumConstant : enumConstants) {
                Object fieldValue = getEnumFieldValue(enumConstant, fieldName);
                if (fieldValue != null) {
                    enumConstantMap.put(fieldValue.toString(), enumConstant);
                }
            }
        }

        @Override
        public T convert(String source) {
            T enumValue = enumConstantMap.get(source);
            if (enumValue == null) {
                throw new IllegalArgumentException("StringToEnumConverter: invalid enum code " + source);
            }
            return enumValue;
        }
    }

    private static String getEnumFieldName(Class<?> enumClass) {
        String fieldName;
        Class<?> fieldType;
        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            fieldName = BaseEnum.code;
            try {
                fieldType = enumClass.getDeclaringClass().getField(fieldName).getType();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        } else {
            Field field = getEnumAnnotationFieldName(enumClass)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("Could not find @EnumValue in Class: %s.", enumClass.getName())));

            fieldType = field.getType();
            fieldName = field.getName();
        }
        return fieldName;
    }

    private static Optional<Field> getEnumAnnotationFieldName(Class<?> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(EnumValue.class))
                .findFirst();
    }

    private static Object getEnumFieldValue(Enum<?> enumClass, String fieldName) {
        try {
            Field field = enumClass.getDeclaringClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(enumClass);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
