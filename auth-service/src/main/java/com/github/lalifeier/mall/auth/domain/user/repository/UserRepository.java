package com.github.lalifeier.mall.auth.domain.user.repository;

import com.github.lalifeier.mall.auth.domain.user.model.entity.UserDO;

public interface UserRepository {

    UserDO findUserByUsername(String username);
}
