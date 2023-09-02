package com.github.lalifeier.mall.cloud.log.function;

import com.github.lalifeier.mall.cloud.log.annotation.OperationLog;

/** 操作日志注解 {@link OperationLog} 的 SpEl 模板中使用的解析函数 */
public interface ParseFunction {

  /**
   * 是否在方法执行前进行解析
   *
   * @return
   */
  default boolean executeBefore() {
    return false;
  }

  /**
   * 获取方法名
   *
   * @return
   */
  String functionName();

  /**
   * 解析数据
   *
   * @param value 待解析的数据
   * @return
   */
  String apply(String value);
}
