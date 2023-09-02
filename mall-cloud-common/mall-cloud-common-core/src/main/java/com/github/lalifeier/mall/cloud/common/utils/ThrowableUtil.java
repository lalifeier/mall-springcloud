package com.github.lalifeier.mall.cloud.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableUtil {

  /**
   * 获取堆栈信息
   *
   * @param throwable the throwable
   * @return the stack trace
   */
  public static String getStackTrace(Throwable throwable) {
    StringWriter sw = new StringWriter();
    try (PrintWriter pw = new PrintWriter(sw)) {
      throwable.printStackTrace(pw);
      return sw.toString();
    }
  }

  /**
   * 转换异常信息为字符串
   *
   * @param exceptionName 异常名称
   * @param exceptionMessage 异常信息
   * @param elements 堆栈信息
   * @return the string
   */
  public static String stackTraceToString(String exceptionName, String exceptionMessage,
      StackTraceElement[] elements) {
    StringBuffer strbuff = new StringBuffer();
    for (StackTraceElement stet : elements) {
      strbuff.append(stet + "\n");
    }
    String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
    return message;
  }
}
