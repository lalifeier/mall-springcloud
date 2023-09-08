package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringToEnumConverter<E extends Enum<E> & BaseEnum<?, String>> implements Converter<String, E> {

    private final Map<String, E> enumConstantMap = new ConcurrentHashMap<>();

    public StringToEnumConverter(Class<E> enumType) {
        E[] enumConstants = enumType.getEnumConstants();
        for (E enumConstant : enumConstants) {
            this.enumConstantMap.put(
                    enumConstant instanceof BaseEnum ? enumConstant.getCode() : String.valueOf(enumConstant.ordinal()),
                    enumConstant);
        }
    }

    @Override
    public E convert(@NotNull String source) {
        E enumValue = enumConstantMap.get(source);
        if (enumValue == null) {
            throw new IllegalArgumentException("invalid enum code " + source);
        }
        return enumValue;
    }
}
