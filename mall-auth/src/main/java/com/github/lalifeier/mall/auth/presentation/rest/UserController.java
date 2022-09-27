package com.github.lalifeier.mall.auth.presentation.rest;


import com.github.lalifeier.mall.auth.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.auth.domain.aggregate.user.entity.User;
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

    @GetMapping("hello")
    public String hello() {
        return "Hello SpringBoot with Swagger3.0";
    }

    @GetMapping
    public List<User> query() {
        return null;
    }

}
