package com.github.lalifeier.mall.cloud.auth.domain.user.repository;

import com.github.lalifeier.mall.cloud.auth.domain.user.model.entity.UserDO;

public interface UserRepository {

    UserDO findUserByUsername(String username);
}
