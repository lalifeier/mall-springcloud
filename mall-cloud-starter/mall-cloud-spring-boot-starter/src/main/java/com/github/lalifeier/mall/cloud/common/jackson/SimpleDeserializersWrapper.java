package com.github.lalifeier.mall.cloud.common.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleDeserializersWrapper extends SimpleDeserializers {
    @Override
    public JsonDeserializer<?> findEnumDeserializer(
            Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> enumDeserializer = super.findEnumDeserializer(type, config, beanDesc);
        if (enumDeserializer != null) {
            return enumDeserializer;
        }

        // 重写增强：开始查找指定枚举类型的接口的反序列化器
        for (Class<?> typeInterface : type.getInterfaces()) {
            enumDeserializer = this._classMappings.get(new ClassKey(typeInterface));
            if (enumDeserializer != null) {
                return enumDeserializer;
            }
        }
        return null;
    }
}
