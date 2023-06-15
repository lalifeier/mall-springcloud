package com.github.lalifeier.mall.cloud.repeatsubmit.annotation;

import com.github.lalifeier.mall.cloud.repeatsubmit.config.RepeatSubmitAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RepeatSubmitAutoConfiguration.class)
public @interface EnableRepeatSubmit {
}
