package com.github.lalifeier.mall.cloud.log.service.impl;

import com.github.lalifeier.mall.cloud.log.factory.ParseFunctionFactory;
import com.github.lalifeier.mall.cloud.log.function.ParseFunction;
import com.github.lalifeier.mall.cloud.log.service.FunctionService;

public class DefaultFunctionServiceImpl implements FunctionService {

  private final ParseFunctionFactory parseFunctionFactory;

  public DefaultFunctionServiceImpl(ParseFunctionFactory parseFunctionFactory) {
    this.parseFunctionFactory = parseFunctionFactory;
  }

  @Override
  public String apply(String functionName, String value) {
    ParseFunction function = parseFunctionFactory.getFunction(functionName);
    if (function == null) {
      return value.toString();
    }
    return function.apply(value);
  }

  @Override
  public boolean beforeFunction(String functionName) {
    return parseFunctionFactory.isBeforeFunction(functionName);
  }
}
