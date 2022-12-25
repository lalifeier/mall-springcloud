package com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.mapper;


import com.github.lalifeier.mall.auth.domain.user.model.entity.User;
import com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserPO, Long> {

    User findUserByUsername(String username);
}
