package com.github.lalifeier.mall.cloud.common.annotation;

import com.github.lalifeier.mall.cloud.common.config.RepeatSubmitAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RepeatSubmitAutoConfiguration.class)
public @interface EnableRepeatSubmit {
}
