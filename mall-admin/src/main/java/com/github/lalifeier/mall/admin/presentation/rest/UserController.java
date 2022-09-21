package com.github.lalifeier.mall.admin.presentation.rest;


import com.github.lalifeier.mall.admin.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;
import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
import com.github.lalifeier.mall.common.service.RedisService;
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

    @Autowired
    RedisService redisService;

    @GetMapping("/hi")
    public Response hi() {
        redisService.set("test", "123");
        int a = 1 / 0;

        throw new BizException("123");
//        return Response.buildSuccess();
    }

    @GetMapping
    public List<User> query() {
        return null;
    }

}
