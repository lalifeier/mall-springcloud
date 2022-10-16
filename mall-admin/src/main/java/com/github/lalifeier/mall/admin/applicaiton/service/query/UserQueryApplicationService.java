package com.github.lalifeier.mall.admin.applicaiton.service.query;


import com.github.lalifeier.mall.admin.domain.user.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
