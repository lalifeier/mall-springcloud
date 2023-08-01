package com.github.lalifeier.mall.cloud.demo.feign;

import com.github.lalifeier.mall.cloud.demo.api.book.BookApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("demo-service")
public interface HelloFeignClient extends BookApi {
}
