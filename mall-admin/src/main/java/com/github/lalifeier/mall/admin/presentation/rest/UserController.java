package com.github.lalifeier.mall.admin.presentation.rest;


import java.util.List;

import com.github.lalifeier.mall.admin.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;
import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
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

    @GetMapping("/hi")
    public Response hi(){

        int a = 1 / 0;

        throw new BizException("xxxxxxxxxxxx");
//        return Response.buildSuccess();
    }
    @GetMapping
    public List<User> query(){
        return null;
    }

}
