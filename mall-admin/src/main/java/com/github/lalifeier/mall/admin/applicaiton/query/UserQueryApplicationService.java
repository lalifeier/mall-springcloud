package com.github.lalifeier.mall.admin.applicaiton.query;


import com.github.lalifeier.mall.admin.domain.aggregate.user.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
