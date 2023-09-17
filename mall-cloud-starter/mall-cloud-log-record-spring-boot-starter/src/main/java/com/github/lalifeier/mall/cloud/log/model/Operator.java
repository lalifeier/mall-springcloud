package com.github.lalifeier.mall.cloud.log.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Operator {
    private String operatorId;
    private String operatorName;
}
