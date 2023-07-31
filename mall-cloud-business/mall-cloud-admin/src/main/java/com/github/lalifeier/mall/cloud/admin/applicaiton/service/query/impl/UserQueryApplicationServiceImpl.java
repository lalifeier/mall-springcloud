package com.github.lalifeier.mall.cloud.admin.applicaiton.service.query.impl;

import com.github.lalifeier.mall.cloud.admin.applicaiton.service.query.UserQueryApplicationService;
import com.github.lalifeier.mall.cloud.admin.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserQueryApplicationServiceImpl implements UserQueryApplicationService {

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return null;
    }
}
