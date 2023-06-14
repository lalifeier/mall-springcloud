package com.github.lalifeier.mall.cloud.demo.interfaces.rest;

import com.github.lalifeier.mall.cloud.common.result.Result;
import com.github.lalifeier.mall.cloud.demo.api.HelloApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloApi {
  @Override
  public String hello(String name) {
    return null;
  }

  //  @EncryptRequest
//  @EncryptResponse
  @PostMapping("/test")
  public Result<String> test(@RequestBody String a) {
    return Result.success(a);
  }
}
