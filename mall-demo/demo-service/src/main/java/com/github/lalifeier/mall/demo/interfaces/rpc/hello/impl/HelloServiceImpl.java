package com.github.lalifeier.mall.demo.interfaces.rpc.hello.impl;

import org.apache.dubbo.config.annotation.DubboService;

import com.github.lalifeier.mall.demo.interfaces.rpc.hello.HelloService;

@DubboService
public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
