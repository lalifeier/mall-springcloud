package com.github.lalifeier.mall.auth.infrastructure.persistent.repository;

import com.github.lalifeier.mall.auth.domain.aggregate.user.repository.UserRepository;
import com.github.lalifeier.mall.auth.infrastructure.persistent.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;
}
