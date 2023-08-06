package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.dao;

import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RolePO, Long> {
}
