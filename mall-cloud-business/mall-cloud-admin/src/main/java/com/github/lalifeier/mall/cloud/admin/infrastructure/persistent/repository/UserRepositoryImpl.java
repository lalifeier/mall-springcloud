package com.github.lalifeier.mall.cloud.admin.infrastructure.persistent.repository;

import com.github.lalifeier.mall.cloud.admin.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.cloud.admin.infrastructure.persistent.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired private UserJpaRepository userJpaRepository;
}
