package com.github.lalifeier.mall.cloud.distributedlock.exception;

public class LockTimeoutException extends RuntimeException {
  public LockTimeoutException(String message) {
    super(message);
  }
}
