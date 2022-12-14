package com.github.lalifeier.mall.common.version;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiAutoConfiguration.class)
public @interface EnableApiVersion {
}

