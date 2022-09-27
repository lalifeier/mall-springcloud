package com.github.lalifeier.mall.demo.presentation.rest;


import com.github.lalifeier.mall.demo.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.demo.infrastructure.persistent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    UserApplicationService userApplicationService;

    @Autowired
    UserQueryApplicationService userQueryApplicationService;

    @GetMapping("")
    public String hi() {
        return "hello world!";
    }

    @GetMapping(value = "/query")
    public List<User> query() {
        return userQueryApplicationService.findAll();
    }

}