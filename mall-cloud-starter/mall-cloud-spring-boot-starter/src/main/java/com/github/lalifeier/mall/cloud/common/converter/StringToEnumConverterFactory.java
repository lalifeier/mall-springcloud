package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 这是一个字符串到枚举类型的转换工厂类。
 * 该工厂类用于获取指定目标类型的字符串到枚举类型的转换器。
 */
public class StringToEnumConverterFactory<E extends Enum<E> & BaseEnum<E, String>>
        implements ConverterFactory<String, E> {

    // 用于存储不同枚举类型的转换器
    private final ConcurrentMap<Class, Converter> CONVERTERS = new ConcurrentHashMap<>();

    @Override
    public <T extends E> Converter<String, T> getConverter(Class<T> targetType) {
        // 检查是否已经存在对应的转换器
        @SuppressWarnings("unchecked")
        Converter<String, T> converter = CONVERTERS.computeIfAbsent(targetType, key -> new StringToEnumConverter(key));
        return converter;
    }
}
