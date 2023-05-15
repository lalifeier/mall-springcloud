package com.github.lalifeier.mall.demo.interfaces.dubbo;


import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloService implements IHelloService {

  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
