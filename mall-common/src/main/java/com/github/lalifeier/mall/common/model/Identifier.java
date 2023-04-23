package com.github.lalifeier.mall.common.model;

import java.io.Serializable;

// ID类型DP的Marker接口
public interface Identifier<T> extends Serializable {
  T getValue();
}
