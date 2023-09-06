package com.github.lalifeier.mall.cloud.common.model.marker;

import java.io.Serializable;

public interface Identifiable<ID extends Identifier> extends Serializable {
    ID getId();
}
