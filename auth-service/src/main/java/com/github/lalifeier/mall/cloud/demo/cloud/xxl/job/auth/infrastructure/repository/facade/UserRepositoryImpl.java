package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.facade;

import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.user.model.entity.UserDO;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.converter.UserConverter;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.mapper.RoleDao;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.mapper.UserDao;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.mapper.UserRoleDao;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final UserRoleDao userRoleDao;

    @Override
    public UserDO findUserByUsername(String username) {
        UserPO user = userDao.findUserByUsername(username);
        return UserConverter.INSTANCE.toDO(user);
    }
}
