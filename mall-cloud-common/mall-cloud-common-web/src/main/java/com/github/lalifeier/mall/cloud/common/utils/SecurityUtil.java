package com.github.lalifeier.mall.cloud.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {


  public static String getUserId() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

//  public static String getUserName() {
//    return null;
//  }
}
