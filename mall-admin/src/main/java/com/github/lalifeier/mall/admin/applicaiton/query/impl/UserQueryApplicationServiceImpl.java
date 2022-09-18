package com.github.lalifeier.mall.admin.applicaiton.query.impl;


import com.github.lalifeier.mall.admin.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserQueryApplicationServiceImpl implements UserQueryApplicationService {



    @RequestMapping("/findAll")
    public List<User> findAll(){
        return null;
    }
}
