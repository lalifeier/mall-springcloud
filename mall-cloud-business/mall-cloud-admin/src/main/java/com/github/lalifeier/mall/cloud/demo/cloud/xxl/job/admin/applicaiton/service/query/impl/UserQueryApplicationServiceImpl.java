package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.applicaiton.service.query.impl;


import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.applicaiton.service.query.UserQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.domain.user.entity.User;
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
