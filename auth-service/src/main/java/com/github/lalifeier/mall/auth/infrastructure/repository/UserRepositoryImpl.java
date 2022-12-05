package com.github.lalifeier.mall.auth.infrastructure.repository;

import com.github.lalifeier.mall.auth.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.mapper.RoleDao;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.mapper.UserDao;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.mapper.UserRoleDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final UserRoleDao userRoleDao;

    //private final UserConverter userConverter;

    public UserRepositoryImpl(UserDao userDao, RoleDao roleDao, UserRoleDao userRoleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.userRoleDao = userRoleDao;
    }


}
