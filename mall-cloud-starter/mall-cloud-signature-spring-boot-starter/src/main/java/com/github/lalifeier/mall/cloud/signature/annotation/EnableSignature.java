package com.github.lalifeier.mall.cloud.signature.annotation;

import com.github.lalifeier.mall.cloud.signature.config.SignatureAutoConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SignatureAutoConfiguration.class})
public @interface EnableSignature {
}
