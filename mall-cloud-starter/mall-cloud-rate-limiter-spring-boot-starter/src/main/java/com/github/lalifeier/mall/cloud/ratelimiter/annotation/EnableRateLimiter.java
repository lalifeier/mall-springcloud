package com.github.lalifeier.mall.cloud.ratelimiter.annotation;

import com.github.lalifeier.mall.cloud.ratelimiter.config.RateLimiterAutoConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RateLimiterAutoConfiguration.class)
public @interface EnableRateLimiter {
}
