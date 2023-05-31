package com.github.lalifeier.mall.sign.config;

import com.github.lalifeier.mall.sign.properties.SignAuthProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableAutoConfiguration
@EnableConfigurationProperties(SignAuthProperties.class)
public class SignAutoConfiguration {
}
