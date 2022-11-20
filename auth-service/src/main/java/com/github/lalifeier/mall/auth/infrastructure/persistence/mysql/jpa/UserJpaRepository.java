package com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.jpa;


import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserPO, Long> {
}
