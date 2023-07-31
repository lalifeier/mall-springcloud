package com.github.lalifeier.mall.cloud.common.annocation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lalifeier.mall.cloud.common.component.DictSerializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = DictSerializer.class)
@JacksonAnnotationsInside
public @interface Dict {

    /** 字典类型 */
    String type();

    /** 字典目标 */
    String target();
}
