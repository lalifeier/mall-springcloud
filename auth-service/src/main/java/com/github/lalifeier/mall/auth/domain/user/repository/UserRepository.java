package com.github.lalifeier.mall.auth.domain.user.repository;

import com.github.lalifeier.mall.auth.domain.user.model.entity.User;

public interface UserRepository {

    User findUserByUsername(String username);
}
