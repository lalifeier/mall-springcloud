package com.github.lalifeier.mall.cloud.log.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operator {
    private String operatorId;
    private String operatorName;
}
