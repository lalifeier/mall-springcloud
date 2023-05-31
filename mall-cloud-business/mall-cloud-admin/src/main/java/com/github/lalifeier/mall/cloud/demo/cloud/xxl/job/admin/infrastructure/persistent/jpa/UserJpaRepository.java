package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.infrastructure.persistent.jpa;


import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.infrastructure.persistent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
