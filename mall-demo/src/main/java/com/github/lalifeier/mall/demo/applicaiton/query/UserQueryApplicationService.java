package com.github.lalifeier.mall.auth.applicaiton.query;


import com.github.lalifeier.mall.auth.infrastructure.persistent.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
