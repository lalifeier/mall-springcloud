package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.applicaiton.service.query;


import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.domain.user.entity.User;

import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
