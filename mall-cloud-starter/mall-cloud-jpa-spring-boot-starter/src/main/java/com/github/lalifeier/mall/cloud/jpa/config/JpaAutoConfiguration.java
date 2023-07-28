package com.github.lalifeier.mall.cloud.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaAutoConfiguration {

  @PersistenceContext
  private final EntityManager entityManager;

  public JpaAutoConfiguration(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}
