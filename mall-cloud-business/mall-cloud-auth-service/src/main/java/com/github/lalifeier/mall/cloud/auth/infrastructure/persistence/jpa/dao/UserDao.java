package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.dao;

import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserPO, Long> {

  UserPO findUserByUsername(String username);

  UserPO findUserByEmail(String email);

  UserPO findUserByPhone(String phone);

  @Query("SELECT u FROM UserPO u WHERE u.username = :usernameOrPhoneOrEmail OR u.phone = :usernameOrPhoneOrEmail OR u.email = :usernameOrPhoneOrEmail")
  UserPO findUserByUsernameOrPhoneOrEmail(@Param("usernameOrPhoneOrEmail") String usernameOrPhoneOrEmail);
}
