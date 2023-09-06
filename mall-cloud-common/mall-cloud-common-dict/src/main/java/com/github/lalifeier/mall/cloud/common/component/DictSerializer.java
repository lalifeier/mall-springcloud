package com.github.lalifeier.mall.cloud.common.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.lalifeier.mall.cloud.common.annocation.Dict;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DictSerializer extends StdSerializer<Object> implements ContextualSerializer {

    private transient String dictType; // 字典类型
    private transient String dictTarget; // 字典目标
    private final Map<String, Map<String, String>> dictMapCache = new ConcurrentHashMap<>(); // 缓存字典映射表

    public DictSerializer() {
        super(Object.class);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        // 获取字典类型和字典目标
        Dict dict = property.getAnnotation(Dict.class);
        if (dict == null) {
            throw new IllegalArgumentException("Missing Dict annotation for property " + property.getName());
        }

        // 创建新的DictSerializer对象，并设置字典类型和字典目标
        DictSerializer dictSerializer = new DictSerializer();
        dictSerializer.setDictType(dict.type());
        dictSerializer.setDictTarget(dict.target());
        return dictSerializer;
    }

    private Map<String, String> loadDictMap(String dictType) {
        // 加载字典映射表
        return null;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.isNull(value)) { // 对象为null时不做处理
            gen.writeNull();
            return;
        }

        try {
            String fieldName = gen.getOutputContext().getCurrentName(); // 获取当前字段名
            String labelFieldName = dictTarget.isEmpty() ? fieldName + "Name" : dictTarget;

            Map<String, String> dictMap =
                    dictMapCache.computeIfAbsent(dictType, key -> loadDictMap(dictType)); // 从缓存中获取字典映射表
            String label = dictMap.get(value.toString()); // 获取字典值

            gen.writeObject(value); // 序列化Java对象
            gen.writeFieldName(labelFieldName); // 写入字段名
            gen.writeString(label); // 写入字典值

        } catch (Exception e) {
            log.error("Error occurred while serializing object: {}", e.getMessage(), e);
        }
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setDictTarget(String dictTarget) {
        this.dictTarget = dictTarget;
    }
}
