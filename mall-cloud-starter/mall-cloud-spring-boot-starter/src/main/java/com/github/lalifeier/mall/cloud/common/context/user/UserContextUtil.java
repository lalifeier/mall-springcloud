package com.github.lalifeier.mall.cloud.common.context.user;

public class UserContextUtil {

  private static final UserContext userContext = UserContextHolder.getUserContext();

  public static Long getUserId() {
    return (userContext != null) ? userContext.getUserId() : null;
  }

  public static String getUserName() {
    return (userContext != null) ? userContext.getUserName() : null;
  }
}
