package com.github.lalifeier.mall.cloud.common.model.ddd;

import java.io.Serializable;

// ID类型DP的Marker接口
public interface Identifier<T> extends Serializable {
  T getValue();
}
