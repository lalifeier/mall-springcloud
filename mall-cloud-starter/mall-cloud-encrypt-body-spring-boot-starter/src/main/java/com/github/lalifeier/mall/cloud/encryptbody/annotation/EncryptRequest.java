package com.github.lalifeier.mall.cloud.encryptbody.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptRequest {
}



