package com.github.lalifeier.mall.auth.infrastructure.repository.facade;

import com.github.lalifeier.mall.auth.domain.user.model.entity.User;
import com.github.lalifeier.mall.auth.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.mapper.RoleDao;
import com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.mapper.UserDao;
import com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.mapper.UserRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final UserRoleDao userRoleDao;

    //private final UserConverter userConverter;
    
    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
