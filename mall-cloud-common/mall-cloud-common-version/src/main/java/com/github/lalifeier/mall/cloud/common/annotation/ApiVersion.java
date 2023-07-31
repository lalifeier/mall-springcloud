package com.github.lalifeier.mall.cloud.common.annotation;

import java.lang.annotation.*;
import org.springframework.web.bind.annotation.Mapping;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    String value() default "1.0.0";

    int platform() default 0;
}
