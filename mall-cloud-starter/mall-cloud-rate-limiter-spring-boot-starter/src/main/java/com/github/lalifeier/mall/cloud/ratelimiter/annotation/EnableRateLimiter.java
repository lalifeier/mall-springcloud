package com.github.lalifeier.mall.cloud.ratelimiter.annotation;

import com.github.lalifeier.mall.cloud.ratelimiter.config.RateLimiterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RateLimiterAutoConfiguration.class)
public @interface EnableRateLimiter {
}
