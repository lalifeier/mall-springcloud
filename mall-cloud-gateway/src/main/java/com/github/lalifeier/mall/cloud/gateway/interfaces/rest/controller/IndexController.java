package com.github.lalifeier.mall.cloud.gateway.interfaces.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class IndexController {


  @GetMapping("/")
  public Map<String, String> index() {
    return Collections.singletonMap("hello", "oauth2.0");
  }
}