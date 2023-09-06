package com.github.lalifeier.mall.cloud.repeatsubmit.annotation;

import com.github.lalifeier.mall.cloud.repeatsubmit.config.RepeatSubmitAutoConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RepeatSubmitAutoConfiguration.class)
public @interface EnableRepeatSubmit {}
