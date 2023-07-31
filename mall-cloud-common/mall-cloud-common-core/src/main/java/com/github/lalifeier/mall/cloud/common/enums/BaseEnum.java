package com.github.lalifeier.mall.cloud.common.enums;

// @JsonDeserialize(using = EnumDeserializer.class)
public interface BaseEnum<T> {

    T getCode();

    String getMessage();
}
