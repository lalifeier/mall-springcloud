package com.github.lalifeier.mall.cloud.common.enums;

import java.io.Serializable;

// @JsonDeserialize(using = EnumDeserializer.class)
public interface BaseEnum<T extends Serializable> {

    T getCode();

    String getDescription();
}
