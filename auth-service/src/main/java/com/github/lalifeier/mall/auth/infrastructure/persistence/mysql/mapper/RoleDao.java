package com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.mapper;


import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RolePO, Long> {
}
