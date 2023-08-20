package com.github.lalifeier.mall.cloud.log.model;

import lombok.Data;

@Data
public class ExecuteResult {
    private boolean success;
    private Throwable throwable;
    private String errorMessage;
    private Object result;
}
