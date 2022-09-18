package com.github.lalifeier.mall.admin.infrastructure.persistent.repository;


import com.github.lalifeier.mall.admin.domain.aggregate.user.repository.UserRepository;
import com.github.lalifeier.mall.admin.infrastructure.persistent.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;
}
