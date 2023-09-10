package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class IntegerToBaseEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum(targetType);
    }

    private class IntegerToEnum<E extends Enum<E> & BaseEnum<E, Integer>> implements Converter<Integer, E> {

        private final Class<E> enumType;

        public IntegerToEnum(Class<E> enumType) {
            this.enumType = enumType;
        }

        @Override
        public E convert(Integer source) {
            return BaseEnum.parse(enumType, source);
        }
    }
}
