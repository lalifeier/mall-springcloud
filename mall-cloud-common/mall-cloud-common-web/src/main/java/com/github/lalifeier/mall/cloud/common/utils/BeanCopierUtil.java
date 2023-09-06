package com.github.lalifeier.mall.cloud.common.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/** 用于将一个对象的属性复制到另一个对象中。 */
public class BeanCopierUtil {
    private static final Map<String, BeanCopier> COPIER_CACHE = new ConcurrentHashMap<>();

    private BeanCopierUtil() {}

    /**
     * 将源对象的属性复制到目标对象中。
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, null);
    }

    /**
     * 将源对象的属性复制到目标对象中，并使用自定义转换器进行属性转换。
     *
     * @param source 源对象
     * @param target 目标对象
     * @param converter 自定义转换器
     */
    public static void copyProperties(Object source, Object target, Converter converter) {
        String key = generateKey(source.getClass(), target.getClass());
        BeanCopier copier =
                COPIER_CACHE.computeIfAbsent(key, k -> BeanCopier.create(source.getClass(), target.getClass(), true));
        copier.copy(source, target, converter);
    }

    /**
     * 生成源类和目标类的唯一键。
     *
     * @param sourceClass 源类
     * @param targetClass 目标类
     * @return 唯一键
     */
    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + targetClass.getName();
    }

    /**
     * 将源对象的属性复制到新创建的目标对象中。
     *
     * @param source 源对象
     * @param targetClass 目标对象的类
     * @return 复制后的目标对象
     */
    public static <T> T copyBean(Object source, Class<T> targetClass) {
        return copyBean(source, targetClass, null);
    }

    /**
     * 将源对象的属性复制到新创建的目标对象中，并使用自定义转换器进行属性转换。
     *
     * @param source 源对象
     * @param targetClass 目标对象的类
     * @param converter 自定义转换器
     * @return 复制后的目标对象
     */
    public static <T> T copyBean(Object source, Class<T> targetClass, Converter converter) {
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create an instance of the target class: " + targetClass.getName(), e);
        }
        copyProperties(source, target, converter);
        return target;
    }

    /**
     * 将源对象列表中的每个对象复制到新创建的目标对象列表中。
     *
     * @param sourceList 源对象列表
     * @param targetClass 目标对象的类
     * @return 复制后的目标对象列表
     */
    public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> targetClass) {
        return copyBeanList(sourceList, targetClass, null);
    }

    /**
     * 将源对象列表中的每个对象复制到新创建的目标对象列表中。
     *
     * @param sourceList 源对象列表
     * @param targetClass 目标对象的类
     * @param converter 自定义转换器
     * @return 复制后的目标对象列表
     */
    public static <T> List<T> copyBeanList(List<?> sourceList, Class<T> targetClass, Converter converter) {
        return sourceList.stream()
                .map(source -> copyBean(source, targetClass, converter))
                .collect(Collectors.toList());
    }
}
