package com.github.lalifeier.mall.demo.interfaces.dubbo;


import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloDubboService implements IHelloService {

  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
