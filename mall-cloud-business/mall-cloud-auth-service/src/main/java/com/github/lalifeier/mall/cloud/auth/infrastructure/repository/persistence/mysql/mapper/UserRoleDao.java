package com.github.lalifeier.mall.cloud.auth.infrastructure.repository.persistence.mysql.mapper;

import com.github.lalifeier.mall.cloud.auth.infrastructure.repository.persistence.mysql.po.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRolePO, Long> {}
