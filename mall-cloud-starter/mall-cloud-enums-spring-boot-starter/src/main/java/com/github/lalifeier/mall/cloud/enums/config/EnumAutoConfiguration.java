package com.github.lalifeier.mall.cloud.enums.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({EnumImportBeanDefinitionRegistrar.class})
public class EnumAutoConfiguration {
}
