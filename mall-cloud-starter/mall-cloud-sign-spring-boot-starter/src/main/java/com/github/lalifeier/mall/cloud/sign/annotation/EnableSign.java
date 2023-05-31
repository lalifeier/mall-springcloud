package com.github.lalifeier.mall.cloud.sign.annotation;

import com.github.lalifeier.mall.cloud.sign.config.SignAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SignAutoConfiguration.class})
public @interface EnableSign {
}
