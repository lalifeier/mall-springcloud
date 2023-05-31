package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.mapper;


import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.repository.persistence.mysql.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserPO, Long> {

    UserPO findUserByUsername(String username);
}
