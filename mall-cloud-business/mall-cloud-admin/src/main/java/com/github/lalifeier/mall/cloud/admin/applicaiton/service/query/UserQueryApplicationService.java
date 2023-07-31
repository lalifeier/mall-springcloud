package com.github.lalifeier.mall.cloud.admin.applicaiton.service.query;

import com.github.lalifeier.mall.cloud.admin.domain.user.entity.User;
import java.util.List;

public interface UserQueryApplicationService {
    List<User> findAll();
}
