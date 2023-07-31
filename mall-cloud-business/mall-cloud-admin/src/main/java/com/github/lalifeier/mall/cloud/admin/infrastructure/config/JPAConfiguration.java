package com.github.lalifeier.mall.cloud.admin.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.github.lalifeier.mall.admin"})
@EntityScan(basePackages = {"com.github.lalifeier.mall.admin"})
public class JPAConfiguration {}
