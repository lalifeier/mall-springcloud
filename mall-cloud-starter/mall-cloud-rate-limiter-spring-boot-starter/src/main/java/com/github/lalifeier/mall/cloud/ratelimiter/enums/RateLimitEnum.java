package com.github.lalifeier.mall.cloud.ratelimiter.enums;

public enum RateLimitEnum {

  //COUNTER("request_rate_limiter", "request_rate_limiter.lua"),

  TOKEN_BUCKET("token_bucket_rate_limiter", "token_bucket_rate_limiter.lua");

  private final String keyName;

  private final String scriptName;

  RateLimitEnum(String keyName, String scriptName) {
    this.keyName = keyName;
    this.scriptName = scriptName;
  }

  public String getKeyName() {
    return this.keyName;
  }

  public String getScriptName() {
    return this.scriptName;
  }
}
