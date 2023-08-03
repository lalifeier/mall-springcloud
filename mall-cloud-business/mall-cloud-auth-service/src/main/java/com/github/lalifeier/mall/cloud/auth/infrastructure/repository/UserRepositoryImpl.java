package com.github.lalifeier.mall.cloud.auth.infrastructure.repository;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.repository.UserRepository;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.converter.UserConverter;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.mapper.RoleDao;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.mapper.UserDao;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.mapper.UserRoleDao;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepositoryImpl implements UserRepository {

  private final UserDao userDao;

  private final RoleDao roleDao;

  private final UserRoleDao userRoleDao;

  private final UserConverter userConverter = UserConverter.INSTANCE;

  @Override
  public UserPrincipal findUserByUsername(String username) {
    UserPO user = userDao.findUserByUsername(username);
    return userConverter.toData(user);
  }
}
