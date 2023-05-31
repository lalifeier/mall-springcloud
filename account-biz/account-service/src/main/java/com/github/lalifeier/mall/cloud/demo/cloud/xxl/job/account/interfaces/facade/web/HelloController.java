package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.interfaces.facade.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello World !";
  }

}
