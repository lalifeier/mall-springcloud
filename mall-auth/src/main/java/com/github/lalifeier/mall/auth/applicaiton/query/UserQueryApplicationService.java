package com.github.lalifeier.mall.auth.applicaiton.query;


import com.github.lalifeier.mall.auth.domain.aggregate.user.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
