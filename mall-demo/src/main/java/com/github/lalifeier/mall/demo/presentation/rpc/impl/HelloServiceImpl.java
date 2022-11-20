package com.github.lalifeier.mall.demo.presentation.rpc.impl;

import com.github.lalifeier.mall.demo.presentation.rpc.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloServiceImpl implements HelloService {
    
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
