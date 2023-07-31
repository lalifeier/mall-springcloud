package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;

public class IntegerToEnumConverter<T extends BaseEnum<Integer>> implements Converter<Integer, T> {

    private final Map<Integer, T> enumConstantMap = new ConcurrentHashMap<>();

    public IntegerToEnumConverter(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            this.enumConstantMap.put(enumConstant.getCode(), enumConstant);
        }
    }

    @Override
    public T convert(@NotNull Integer source) {
        T enumValue = enumConstantMap.get(source);
        if (enumValue == null) {
            throw new IllegalArgumentException(
                    "IntegerToEnumConverter: invalid enum code " + source);
        }
        return enumValue;
    }
}
