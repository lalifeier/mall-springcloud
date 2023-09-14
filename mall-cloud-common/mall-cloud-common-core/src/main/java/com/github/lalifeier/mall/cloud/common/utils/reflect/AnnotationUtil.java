package com.github.lalifeier.mall.cloud.common.utils.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnnotationUtil {
    /**
     * 递归获取class及其所有父类和接口的Annotation.
     * 支持Spring风格的Annotation继承的父Annotation
     *
     * @param cls 要获取注解的类
     * @return 类及其父类和接口中的所有注解集合
     */
    public static Set<Annotation> getAllAnnotations(final Class<?> cls) {
        List<Class<?>> allTypes = ClassUtils.getAllSuperclasses(cls);
        allTypes.addAll(ClassUtils.getAllInterfaces(cls));
        allTypes.add(cls);

        Set<Annotation> annotations = new HashSet<>();
        for (Class<?> type : allTypes) {
            annotations.addAll(Arrays.asList(type.getDeclaredAnnotations()));
        }

        Set<Annotation> superAnnotations = new HashSet<>();
        for (Annotation annotation : annotations) {
            getSuperAnnotations(annotation.annotationType(), superAnnotations);
        }

        annotations.addAll(superAnnotations);

        return annotations;
    }

    /**
     * 递归获取指定注解类型的父注解，支持Spring风格的注解继承。
     *
     * @param annotationType 注解类型
     * @param visited 已访问的注解集合
     * @param <A> 注解类型
     */
    private static <A extends Annotation> void getSuperAnnotations(Class<A> annotationType, Set<Annotation> visited) {
        Annotation[] annotations = annotationType.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (!annotation.annotationType().getName().startsWith("java.lang") && visited.add(annotation)) {
                getSuperAnnotations(annotation.annotationType(), visited);
            }
        }
    }

    /**
     * 获取标注了指定Annotation的公共属性，循环遍历父类.
     * 暂未支持Spring风格Annotation继承Annotation
     *
     * @param clazz      要检查属性的类
     * @param annotation 要查找的注解类型
     * @param <T>        注解类型
     * @return 标注了指定注解的公共属性集合
     */
    public static <T extends Annotation> Set<Field> getAnnotatedPublicFields(Class<?> clazz, Class<T> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }

        Set<Field> annotatedFields = new HashSet<>();
        Field[] fields = clazz.getFields();

        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields;
    }
    /**
     * 获取标注了指定Annotation的属性，循环遍历父类，包含private属性.
     * 暂未支持Spring风格Annotation继承Annotation
     *
     * @param clazz      要检查属性的类
     * @param annotation 要查找的注解类型
     * @param <T>        注解类型
     * @return 标注了指定注解的属性集合
     */
    public static <T extends Annotation> Set<Field> getAnnotatedFields(Class<?> clazz, Class<T> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }

        Set<Field> annotatedFields = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                annotatedFields.add(field);
            }
        }

        annotatedFields.addAll(getAnnotatedFields(clazz.getSuperclass(), annotation));

        return annotatedFields;
    }

    /**
     * 找出所有标注了该annotation的公共方法(含父类的公共函数)，循环其接口.
     *
     * 暂未支持Spring风格Annotation继承Annotation
     *
     * 另，如果子类重载父类的公共函数，父类函数上的annotation不会继承，只有接口上的annotation会被继承.
     *
     * @param clazz      要查找方法的类
     * @param annotation 要查找的注解类型
     * @param <T>        注解类型
     * @return 标注了指定注解的公共方法集合
     */
    public static <T extends Annotation> Set<Method> getAnnotatedPublicMethods(Class<?> clazz, Class<T> annotation) {
        // 已递归到 Object.class，停止递归
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }

        List<Class<?>> ifcs = ClassUtils.getAllInterfaces(clazz);
        Set<Method> annotatedMethods = new HashSet<>();

        // 遍历当前类的所有公共方法
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            // 如果当前方法有标注，或定义了该方法的所有接口有标注
            if (method.isAnnotationPresent(annotation) || searchOnInterfaces(method, annotation, ifcs)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }

    /**
     * 在接口中搜索方法是否存在标注了指定注解的方法.
     *
     * @param method         要搜索的方法
     * @param annotationType 要查找的注解类型
     * @param ifcs           接口列表
     * @param <T>            注解类型
     * @return 如果找到标注了指定注解的方法，则返回 true；否则返回 false
     */
    private static <T extends Annotation> boolean searchOnInterfaces(
            Method method, Class<T> annotationType, List<Class<?>> ifcs) {
        for (Class<?> iface : ifcs) {
            try {
                Method equivalentMethod = iface.getMethod(method.getName(), method.getParameterTypes());
                if (equivalentMethod.isAnnotationPresent(annotationType)) {
                    return true;
                }
            } catch (NoSuchMethodException ex) {
                // Skip this interface - it doesn't have the method...
            }
        }
        return false;
    }
}
