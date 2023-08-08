package com.github.lalifeier.mall.cloud.distributedlock.annotation;

import com.github.lalifeier.mall.cloud.distributedlock.config.DistributedLockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DistributedLockAutoConfiguration.class)
public @interface EnableDistributedLock {
}
