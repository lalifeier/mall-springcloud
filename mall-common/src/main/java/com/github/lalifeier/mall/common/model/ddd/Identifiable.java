package com.github.lalifeier.mall.common.model.ddd;

import java.io.Serializable;

public interface Identifiable<ID extends Identifier> extends Serializable {
  ID getId();
}
