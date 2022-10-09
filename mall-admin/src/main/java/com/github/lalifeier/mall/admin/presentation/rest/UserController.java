package com.github.lalifeier.mall.admin.presentation.rest;


import com.github.lalifeier.mall.admin.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;
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

//    @Autowired
//    RedisService redisService;

    @GetMapping("hello")
    public String hello() {

//        int a = 4 / 0;
        return "Hello SpringBoot with Swagger3.0";
    }

    @GetMapping
    public List<User> query() {
        return null;
    }

}
