package com.github.lalifeier.mall.demo.interfaces.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name;
    }


}
