package com.github.lalifeier.mall.admin.presentation.rest;


import com.github.lalifeier.mall.admin.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    UserApplicationService userApplicationService;

    //@Autowired
    //UserQueryApplicationService userQueryApplicationService;
    //
    //@GetMapping
    //public List<User> query() {
    //    return null;
    //}

    //@GetMapping("/currentUser")
    //public User currentUser(HttpServletRequest request) {
    //    // 从Header中获取用户信息
    //    String userStr = request.getHeader("user");
    //    JSONObject userJsonObject = new JSONObject(userStr);
    //    return User.builder()
    //            .username(userJsonObject.getStr("user_name"))
    //            .id(Convert.toLong(userJsonObject.get("id")))
    //            .roles(Convert.toList(String.class, userJsonObject.get("authorities"))).build();
    //}

    //@GetMapping
    //public JSONObject findUser(HttpServletRequest request) {
    //    //从Header中获取用户信息
    //    String userStr = request.getHeader("user");
    //    return new JSONObject(userStr);
    //}

}
