package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 整数到枚举类型的转换工厂类。
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum<?, Integer>> {
    // 用于存储不同枚举类型的转换器
    private final ConcurrentMap<Class<? extends BaseEnum<?, Integer>>, IntegerToEnumConverter<?>> CONVERTERS =
            new ConcurrentHashMap<>();

    /**
     * 获取指定目标类型的整数到枚举类型的转换器。
     *
     * @param targetType 目标类型
     * @param <T>        枚举类型
     * @return 整数到枚举类型的转换器
     */
    @Override
    public <T extends BaseEnum<?, Integer>> Converter<Integer, T> getConverter(@NotNull Class<T> targetType) {
        // 使用computeIfAbsent方法来避免竞争条件
        @SuppressWarnings("unchecked")
        IntegerToEnumConverter<T> converter = (IntegerToEnumConverter<T>)
                CONVERTERS.computeIfAbsent(targetType, key -> new IntegerToEnumConverter<>(key));
        return converter;
    }
}
