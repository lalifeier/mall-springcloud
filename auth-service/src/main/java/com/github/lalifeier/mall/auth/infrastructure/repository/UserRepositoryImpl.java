package com.github.lalifeier.mall.auth.infrastructure.repository;

import com.github.lalifeier.mall.auth.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.converter.UserConverter;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.jpa.RoleJpaRepository;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.jpa.UserJpaRepository;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.jpa.UserRoleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final RoleJpaRepository roleJpaRepository;

    private final UserRoleJpaRepository userRoleJpaRepository;

    private final UserConverter userConverter;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, UserRoleJpaRepository userRoleJpaRepository, UserConverter userConverter) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.userRoleJpaRepository = userRoleJpaRepository;
        this.userConverter = userConverter;
    }


}
