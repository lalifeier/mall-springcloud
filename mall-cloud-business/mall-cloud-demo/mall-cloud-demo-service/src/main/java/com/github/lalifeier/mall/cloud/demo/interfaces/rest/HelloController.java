package com.github.lalifeier.mall.cloud.demo.interfaces.rest;

import com.github.lalifeier.mall.cloud.demo.api.HelloApi;
import com.github.lalifeier.mall.cloud.encryptbody.annotation.EncryptResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloApi {
  @Override
  public String hello(String name) {
    return null;
  }


  @EncryptResponse
  @PostMapping("/test")
  public String test() {
    return "123456";
  }
}
