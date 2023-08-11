package com.github.lalifeier.mall.cloud.jpa.config;

import com.github.lalifeier.mall.cloud.common.context.UserContextUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaAutoConfiguration {

    @PersistenceContext private final EntityManager entityManager;

    public JpaAutoConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(UserContextUtil.getUserId());
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
