package com.github.lalifeier.mall.cloud.log.service;

public interface FunctionService {

    String apply(String functionName, String value);

    boolean beforeFunction(String functionName);
}
