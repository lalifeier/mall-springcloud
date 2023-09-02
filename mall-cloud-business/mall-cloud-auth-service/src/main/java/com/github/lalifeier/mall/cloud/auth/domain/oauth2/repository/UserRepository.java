package com.github.lalifeier.mall.cloud.auth.domain.oauth2.repository;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;

public interface UserRepository {

  UserPrincipal findByUsername(String username);

  UserPrincipal findByEmail(String email);

  UserPrincipal findByPhone(String phone);

  UserPrincipal findByUsernameOrPhoneOrEmail(String usernameOrPhoneOrEmail);
}
