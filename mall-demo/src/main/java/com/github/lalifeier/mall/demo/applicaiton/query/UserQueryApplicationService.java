package com.github.lalifeier.mall.demo.applicaiton.query;


import com.github.lalifeier.mall.demo.infrastructure.persistent.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
