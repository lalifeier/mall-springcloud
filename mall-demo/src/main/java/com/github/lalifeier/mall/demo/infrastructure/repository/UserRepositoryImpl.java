package com.github.lalifeier.mall.demo.infrastructure.repository;

import com.github.lalifeier.mall.demo.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.user.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Resource
  private UserMapper userMapper;
}
