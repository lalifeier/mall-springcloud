package com.github.lalifeier.mall.account.interfaces.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalifeier.mall.account.infrastructure.persistence.jpa.entity.UserEntity;
import com.github.lalifeier.mall.common.utils.JsonUtils;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/currentUser")
    public UserEntity currentUser(HttpServletRequest request) {
        //从Header中获取用户信息
        String userStr = request.getHeader("user");
        UserEntity user = JsonUtils.json2Bean(userStr, UserEntity.class);
        return user;
    }

    @GetMapping
    public String findUser(HttpServletRequest request) {
        //从Header中获取用户信息
        String userStr = request.getHeader("user");
        return userStr;
    }
}
