package com.github.lalifeier.mall.cloud.common.utils.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassUtil extends ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    /**
     * 判断一个类是否是另一个类的子类或子接口。
     *
     * @param subclass 子类
     * @param superclass 父类
     * @return 如果 subclass 是 superclass 的子类或子接口，则返回 true；否则返回 false
     */
    public static boolean isSubClassOrInterfaceOf(Class<?> subclass, Class<?> superclass) {
        return superclass.isAssignableFrom(subclass);
    }

    /**
     * 获取经过 CGLib 处理后的对象的原始类。
     *
     * @param instance 对象实例
     * @return 经过 CGLib 处理后的对象的原始类
     */
    public static Class<?> unwrapCglib(Object instance) {
        Validate.notNull(instance, "Instance must not be null");
        Class<?> clazz = instance.getClass();
        if (clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass)) {
                return superClass;
            }
        }
        return clazz;
    }

    /**
     * 通过反射获取类定义中声明的泛型参数的类型。
     *
     * @param clazz 类
     * @return 第一个泛型参数的类型，如果无法确定则返回 Object.class
     */
    public static Class<?> getClassGenericType(final Class<?> clazz) {
        return getClassGenericType(clazz, 0);
    }

    /**
     * 通过反射获取类定义中声明的父类的泛型参数的类型。
     *
     * @param clazz 类
     * @param index 泛型参数的索引，从 0 开始
     * @return 第 index 个泛型参数的类型，如果无法确定则返回 Object.class
     */
    public static Class<?> getClassGenericType(final Class<?> clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index < 0 || index >= params.length) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class<?>) params[index];
    }

    /**
     * 检查给定的 Class 对象是否为枚举类型。
     *
     * @param clazz 要检查的 Class 对象
     * @return 如果给定的 Class 对象是枚举类型，则返回 true；否则返回 false
     */
    public static boolean isEnum(Class<?> clazz) {
        return clazz != null && clazz.isEnum();
    }

    /**
     * 检查给定的对象是否为枚举类型。
     *
     * @param obj 要检查的对象
     * @return 如果给定的对象是枚举类型，则返回 true；否则返回 false
     */
    public static boolean isEnum(Object obj) {
        return obj != null && obj.getClass().isEnum();
    }
}
