package com.github.lalifeier.mall.cloud.common.utils.reflect;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassLoaderUtil {
    /**
     * Copy from Spring, 按顺序获取默认ClassLoader
     *
     * 1. Thread.currentThread().getContextClassLoader()
     * 2. ClassLoaderUtil的加载ClassLoader
     * 3. SystemClassLoader
     *
     * @return 默认的ClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 无法访问线程上下文的ClassLoader
        }
        if (cl == null) {
            cl = ClassLoaderUtil.class.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // 无法访问系统ClassLoader
                }
            }
        }
        return cl;
    }

    /**
     * 探测类是否存在于classpath中
     *
     * @param className    类名
     * @param classLoader  ClassLoader
     * @return 类是否存在于classpath中
     */
    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            classLoader.loadClass(className);
            return true;
        } catch (Throwable ex) {
            // 类或其依赖项不存在
            return false;
        }
    }
}
