package com.github.lalifeier.mall.account.controller;

import com.github.lalifeier.mall.account.domain.User;
import com.github.lalifeier.utils.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/currentUser")
    public User currentUser(HttpServletRequest request) {
        //从Header中获取用户信息
        String userStr = request.getHeader("user");
        User user = JsonUtils.json2Bean(userStr, User.class);
        return user;
    }

    @GetMapping
    public String findUser(HttpServletRequest request) {
        //从Header中获取用户信息
        String userStr = request.getHeader("user");
        return userStr;
    }
}

