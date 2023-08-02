package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.mapper;

import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserPO, Long> {

  UserPO findUserByUsername(String username);
}
