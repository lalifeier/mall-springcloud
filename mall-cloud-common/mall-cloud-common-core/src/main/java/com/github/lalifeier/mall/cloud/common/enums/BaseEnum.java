package com.github.lalifeier.mall.cloud.common.enums;

import java.util.Arrays;
import java.util.Objects;

public interface BaseEnum<E extends Enum<E>, T> {

    T getCode();

    static <E extends Enum<E> & BaseEnum<E, T>, T> E parse(Class<E> clazz, T code) {
        if (code == null || code.toString().isEmpty()) {
            return null;
        }

        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> Objects.equals(e.getCode(), code))
                .findFirst()
                .orElse(null);
    }
}
