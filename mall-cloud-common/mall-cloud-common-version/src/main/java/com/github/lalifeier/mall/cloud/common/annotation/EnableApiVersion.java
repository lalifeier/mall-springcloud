package com.github.lalifeier.mall.cloud.common.annotation;

import com.github.lalifeier.mall.cloud.common.config.ApiAutoConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiAutoConfiguration.class)
public @interface EnableApiVersion {
}
