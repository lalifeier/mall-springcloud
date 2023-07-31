package com.github.lalifeier.mall.cloud.demo.feign;

import com.github.lalifeier.mall.cloud.demo.book.api.BookApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("demo-service")
public interface HelloFeignClient extends BookApi {}
