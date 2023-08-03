package com.github.lalifeier.mall.cloud.auth.domain.oauth2.repository;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;

public interface UserRepository {

  UserPrincipal findUserByUsername(String username);
}
