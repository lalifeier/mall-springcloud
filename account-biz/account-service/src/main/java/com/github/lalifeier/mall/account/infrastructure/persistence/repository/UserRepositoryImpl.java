package com.github.lalifeier.mall.account.infrastructure.persistence.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.lalifeier.mall.account.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.account.infrastructure.persistence.jpa.repository.UserJpaRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
  @Resource
  private UserJpaRepository userJpaRepository;

}
