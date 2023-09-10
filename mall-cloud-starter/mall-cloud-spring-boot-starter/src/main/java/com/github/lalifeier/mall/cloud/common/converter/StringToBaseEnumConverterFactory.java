package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        if (!targetType.isEnum()) {}

        return new StringToEnum(targetType);
    }

    private class StringToEnum<E extends Enum<E> & BaseEnum<E, String>> implements Converter<String, E> {

        private final Class<E> enumType;

        public StringToEnum(Class<E> enumType) {
            this.enumType = enumType;
        }

        @Override
        public E convert(String source) {
            return BaseEnum.parse(enumType, source);
        }
    }
}
