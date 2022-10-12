package com.github.lalifeier.mall.account.controller;


import com.github.lalifeier.mall.account.vo.ValidVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

//        Assert.notNull(null, "");

        return "Hello World !";
    }

    @PostMapping("/valid")
    public String valid(@Validated @RequestBody ValidVO validVO) {
        return "Hello World !";
    }

}

