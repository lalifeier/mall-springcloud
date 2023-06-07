package com.github.lalifeier.mall.cloud.common.annotation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.lalifeier.mall.cloud.common.factory.IEnumDeserializer;

import java.io.Serializable;

@JsonDeserialize(using = IEnumDeserializer.class)
public interface IEnum<T extends Serializable> {
  T getValue();
}

