package com.github.lalifeier.mall.cloud.signature.annotation;

import com.github.lalifeier.mall.cloud.signature.config.SignatureAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SignatureAutoConfiguration.class})
public @interface EnableSignature {
}
