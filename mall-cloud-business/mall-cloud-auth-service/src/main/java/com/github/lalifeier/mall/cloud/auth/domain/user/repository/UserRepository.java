package com.github.lalifeier.mall.cloud.auth.domain.user.repository;

import com.github.lalifeier.mall.cloud.auth.domain.user.model.entity.User;

public interface UserRepository {

  User findUserByUsername(String username);
}
