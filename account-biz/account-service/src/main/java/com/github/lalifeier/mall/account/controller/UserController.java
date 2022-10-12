package com.github.lalifeier.mall.account.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.github.lalifeier.mall.account.domain.User;
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
        JSONObject userJsonObject = new JSONObject(userStr);
        return User.builder()
                .username(userJsonObject.getStr("user_name"))
                .id(Convert.toLong(userJsonObject.get("id")))
                .roles(Convert.toList(String.class, userJsonObject.get("authorities"))).build();
    }

    @GetMapping
    public JSONObject findUser(HttpServletRequest request) {
        //从Header中获取用户信息
        String userStr = request.getHeader("user");
        return new JSONObject(userStr);
    }
}

