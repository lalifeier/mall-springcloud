package com.github.lalifeier.mall.auth.applicaiton.query.impl;


import com.github.lalifeier.mall.auth.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.auth.domain.aggregate.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class UserQueryApplicationServiceImpl implements UserQueryApplicationService {


    @RequestMapping("/findAll")
    public List<User> findAll() {
        return null;
    }
}
