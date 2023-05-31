package com.github.lalifeier.mall.cloud.account.facade.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello World !";
  }

}
