package com.github.lalifeier.mall.cloud.demo.interfaces.dubbo;


import com.github.lalifeier.mall.cloud.demo.dubbo.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloDubboService implements IHelloService {

  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
