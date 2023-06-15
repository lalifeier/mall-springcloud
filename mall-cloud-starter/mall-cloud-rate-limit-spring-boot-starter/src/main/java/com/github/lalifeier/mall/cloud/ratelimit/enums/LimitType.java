package com.github.lalifeier.mall.cloud.ratelimit.enums;

import com.github.lalifeier.mall.cloud.ratelimit.annotation.RateLimit;

public enum LimitType {
  /**
   * 默认策略全局限流  不区分IP和用户
   */
  GLOBAL {
    @Override
    public String generateCombinedKey(RateLimit rateLimiter) {
      return rateLimiter.key() + this.name();
    }
  };

  /**
   * 根据请求者IP进行限流
   */
//  IP {
//    @Override
//    public String generateCombinedKey(RateLimit rateLimiter) {
//      String clientIP = WebUtils.getServerHttpRequestIpAddress(ServletHolderUtil.getRequest());
//      return rateLimiter.key() + clientIP;
//    }
//  };

  /**
   * 按用户限流
   */
//  USER {
//    @Override
//    public String generateCombinedKey(RateLimit rateLimiter) {
//      LoginUser loginUser = AuthenticationUtils.getLoginUser();
//      if (loginUser == null) {
//        throw new ApiException(ErrorCode.Client.COMMON_NO_AUTHORIZATION);
//      }
//      return rateLimiter.key() + loginUser.getUsername();
//    }
//  };
  public abstract String generateCombinedKey(RateLimit rateLimiter);
}
