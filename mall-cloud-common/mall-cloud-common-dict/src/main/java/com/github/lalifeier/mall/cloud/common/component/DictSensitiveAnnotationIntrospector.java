package com.github.lalifeier.mall.cloud.common.component;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.github.lalifeier.mall.cloud.common.annocation.Dict;
import org.springframework.stereotype.Component;

/** 字典注解序列化拦截器 */
@Component
public class DictSensitiveAnnotationIntrospector extends NopAnnotationIntrospector {

    private static final long serialVersionUID = 1L;

    @Override
    public Object findSerializer(Annotated am) {
        Dict dict = am.getAnnotation(Dict.class);
        if (dict != null) {
            return DictSerializer.class;
        }
        return null;
    }
}
