package com.github.lalifeier.mall.cloud.log.factory;

import com.github.lalifeier.mall.cloud.log.function.ParseFunction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ParseFunctionFactory {
  private Map<String, ParseFunction> allFunctionMap;

  public ParseFunctionFactory(List<ParseFunction> parseFunctions) {
    if (CollectionUtils.isEmpty(parseFunctions)) {
      return;
    }
    allFunctionMap = new HashMap<>();
    for (ParseFunction parseFunction : parseFunctions) {
      if (StringUtils.isEmpty(parseFunction.functionName())) {
        continue;
      }
      allFunctionMap.put(parseFunction.functionName(), parseFunction);
    }
  }

  public ParseFunction getFunction(String functionName) {
    return allFunctionMap.get(functionName);
  }

  public boolean isBeforeFunction(String functionName) {
    return allFunctionMap.get(functionName) != null
        && allFunctionMap.get(functionName).executeBefore();
  }
}
