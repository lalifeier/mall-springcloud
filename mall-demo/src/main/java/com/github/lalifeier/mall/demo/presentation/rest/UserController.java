package com.github.lalifeier.mall.auth.presentation.rest;


import java.util.List;

import com.github.lalifeier.mall.auth.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.auth.infrastructure.persistent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    UserApplicationService userApplicationService;

    @Autowired
    UserQueryApplicationService userQueryApplicationService;

    @GetMapping("")
    public String hi(){
        return "hello world!";
    }

    @GetMapping(value ="/query")
    public List<User> query(){
        return userQueryApplicationService.findAll();
    }

}