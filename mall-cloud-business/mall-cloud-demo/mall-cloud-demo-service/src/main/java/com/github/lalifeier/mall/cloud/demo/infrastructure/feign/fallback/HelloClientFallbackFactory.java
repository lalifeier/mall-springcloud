package com.github.lalifeier.mall.cloud.demo.infrastructure.feign.fallback;

import com.github.lalifeier.mall.cloud.demo.infrastructure.feign.HelloClient;
import org.springframework.cloud.openfeign.FallbackFactory;

public class HelloClientFallbackFactory implements FallbackFactory<HelloClient> {
  @Override
  public HelloClient create(Throwable throwable) {
    HelloClientFallback accountClientFallback = new HelloClientFallback();
    accountClientFallback.setCause(throwable);
    return accountClientFallback;
  }
}
