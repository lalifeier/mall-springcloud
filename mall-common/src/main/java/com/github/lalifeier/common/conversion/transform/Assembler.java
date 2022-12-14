package com.github.lalifeier.common.conversion.transform;

public interface Assembler<R, T> {
    public T convert(R original, Class<T> targetType);
}
