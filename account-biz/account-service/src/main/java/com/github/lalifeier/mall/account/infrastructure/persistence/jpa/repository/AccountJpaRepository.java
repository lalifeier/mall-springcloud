package com.github.lalifeier.mall.account.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lalifeier.mall.account.infrastructure.persistence.jpa.entity.AccountEntity;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

}
