package com.github.lalifeier.common.result;

import lombok.Data;

@Data
public class BaseResult {

    private boolean success;

    private int errCode;

    private String errMessage;

    private String traceId;
}
