package com.github.lalifeier.mall.cloud.cache.publisher;

public interface MessagePublisher<T> {

  void publish(T data);
}
