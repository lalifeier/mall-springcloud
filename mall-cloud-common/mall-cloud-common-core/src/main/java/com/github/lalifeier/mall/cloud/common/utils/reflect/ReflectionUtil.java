package com.github.lalifeier.mall.cloud.common.utils.reflect;

import com.github.lalifeier.mall.cloud.common.utils.ObjectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {
    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static final String IS_PREFIX = "is";

    /**
     * 循环遍历，按属性名获取前缀为 set 的函数，并设为可访问。
     *
     * @param clazz          目标类
     * @param propertyName   属性名
     * @param parameterType  参数类型
     * @return 对应的 setter 方法
     */
    public static Method getSetterMethod(Class<?> clazz, String propertyName, Class<?> parameterType) {
        String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);
        return getMethod(clazz, setterMethodName, parameterType);
    }

    /**
     * 循环遍历，按属性名获取前缀为 get 或 is 的函数，并设为可访问。
     *
     * @param clazz         目标类
     * @param propertyName  属性名
     * @return 对应的 getter 方法
     */
    public static Method getGetterMethod(Class<?> clazz, String propertyName) {
        String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);
        Method method = getMethod(clazz, getterMethodName);

        // retry on another name
        if (method == null) {
            getterMethodName = IS_PREFIX + StringUtils.capitalize(propertyName);
            method = getMethod(clazz, getterMethodName);
        }
        return method;
    }

    /**
     * 循环向上转型，获取对象的 DeclaredMethod，并强制设置为可访问。
     *
     * @param clazz           目标类
     * @param methodName      方法名
     * @param parameterTypes  参数类型
     * @return 对应的方法
     */
    public static Method getMethod(final Class<?> clazz, final String methodName, Class<?>... parameterTypes) {
        Method method = MethodUtils.getMatchingMethod(clazz, methodName, parameterTypes);
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
     *
     * 如向上转型到Object仍无法找到, 返回null.
     *
     * 只匹配函数名, 如果有多个同名函数返回第一个
     *
     * 方法需要被多次调用时，先使用本函数先取得Method，然后调用Method.invoke(Object obj, Object... args)
     *
     * 因为getMethod() 不能获取父类的private函数, 因此采用循环向上的getDeclaredMethods()
     *
     * @param clazz       目标类
     * @param methodName  方法名
     * @return 可访问的方法
     */
    public static Method getAccessibleMethodByName(final Class<?> clazz, final String methodName) {
        Validate.notNull(clazz, "clazz can't be null");
        Validate.notEmpty(methodName, "methodName can't be blank");

        Class<?> searchType = clazz;
        while (searchType != Object.class) {
            Method[] methods = searchType.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    makeAccessible(method);
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField, 并强制设置为可访问.
     *
     * 如向上转型到 Object 仍无法找到, 返回 null.
     *
     * 因为 getField() 不能获取父类的 private 属性, 因此采用循环向上的 getDeclaredField();
     *
     * @param clazz      目标类
     * @param fieldName  字段名
     * @return 与字段名匹配的可访问字段
     */
    public static Field getField(final Class<?> clazz, final String fieldName) {
        Validate.notNull(clazz, "clazz can't be null");
        Validate.notEmpty(fieldName, "fieldName can't be blank");
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 调用Getter方法, 无视private/protected修饰符.
     *
     * 性能较差, 用于单次调用的场景
     */
    public static <T> T invokeGetter(Object obj, String propertyName) {
        Method method = getGetterMethod(obj.getClass(), propertyName);
        if (method == null) {
            throw new IllegalArgumentException(
                    "Could not find getter method [" + propertyName + "] on target [" + obj + ']');
        }
        return invokeMethod(obj, method);
    }

    /**
     * 调用Setter方法, 无视private/protected修饰符, 按传入value的类型匹配函数.
     *
     * 性能较差, 用于单次调用的场景
     */
    public static void invokeSetter(Object obj, String propertyName, Object value) {
        Method method = getSetterMethod(obj.getClass(), propertyName, value.getClass());
        if (method == null) {
            throw new IllegalArgumentException(
                    "Could not find getter method [" + propertyName + "] on target [" + obj + ']');
        }
        invokeMethod(obj, method, value);
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     *
     * 性能较差, 用于单次调用的场景
     *
     * @param obj       目标对象
     * @param fieldName 字段名
     * @param <T>       返回值类型
     * @return 字段的值
     * @throws IllegalArgumentException 如果在目标对象中找不到指定的字段
     */
    public static <T> T getFieldValue(Object obj, String fieldName) {
        Field field = getField(obj.getClass(), fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }
        return getFieldValue(obj, field);
    }

    /**
     * 使用已获取的Field, 直接读取对象属性值, 不经过getter函数.
     *
     * 用于反复调用的场景.
     *
     * @param obj   目标对象
     * @param field 字段
     * @param <T>   返回值类型
     * @return 字段的值
     * @throws RuntimeException 如果无法读取字段的值
     */
    public static <T> T getFieldValue(Object obj, Field field) {
        try {
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get field value: " + field.getName(), e);
        }
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     *
     * 性能较差, 用于单次调用的场景
     *
     * @param obj       目标对象
     * @param fieldName 字段名
     * @param value     字段值
     * @throws IllegalArgumentException 如果在目标对象中找不到指定的字段
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Field field = getField(obj.getClass(), fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }
        setField(obj, field, value);
    }

    /**
     * 使用预先获取的Field, 直接设置对象属性值, 不经过setter函数.
     *
     * 用于反复调用的场景.
     *
     * @param obj   目标对象
     * @param field 字段
     * @param value 字段值
     * @throws RuntimeException 如果无法设置字段的值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set field value: " + field.getName(), e);
        }
    }

    /**
     * 先尝试用Getter函数读取, 如果不存在则直接读取变量.
     *
     * 性能较差, 用于单次调用的场景
     *
     * @param obj          目标对象
     * @param propertyName 属性名
     * @param <T>          返回值类型
     * @return 属性的值
     */
    public static <T> T getProperty(Object obj, String propertyName) {
        Method method = getGetterMethod(obj.getClass(), propertyName);
        if (method != null) {
            return invokeMethod(obj, method);
        } else {
            return getFieldValue(obj, propertyName);
        }
    }

    /**
     * 先尝试用Setter函数写入, 如果不存在则直接写入变量, 按传入value的类型匹配函数.
     *
     * 性能较差, 用于单次调用的场景
     *
     * @param obj          目标对象
     * @param propertyName 属性名
     * @param value        属性值
     */
    public static void setProperty(Object obj, String propertyName, Object value) {
        Method method = getSetterMethod(obj.getClass(), propertyName, value.getClass());
        if (method != null) {
            invokeMethod(obj, method, value);
        } else {
            setFieldValue(obj, propertyName, value);
        }
    }

    /**
     * 反射调用对象方法, 无视 private/protected 修饰符.
     *
     * 根据传入参数的实际类型进行匹配, 支持方法参数定义是接口，父类，原子类型等情况
     *
     * 性能较差，仅用于单次调用.
     *
     * @param obj         目标对象
     * @param methodName  方法名
     * @param args        方法参数
     * @param <T>         返回值类型
     * @return 方法的返回值
     */
    public static <T> T invokeMethod(Object obj, String methodName, Object... args) {
        Object[] theArgs = ArrayUtils.nullToEmpty(args);
        final Class<?>[] parameterTypes = ClassUtils.toClass(theArgs);
        return invokeMethod(obj, methodName, theArgs, parameterTypes);
    }

    /**
     * 反射调用对象方法, 无视 private/protected 修饰符.
     *
     * 根据参数类型进行匹配, 支持方法参数定义是接口，父类，原子类型等情况
     *
     * 性能较低，仅用于单次调用.
     *
     * @param obj             目标对象
     * @param methodName      方法名
     * @param args            方法参数
     * @param parameterTypes  方法参数类型
     * @param <T>             返回值类型
     * @return 方法的返回值
     */
    public static <T> T invokeMethod(
            final Object obj, final String methodName, final Object[] args, final Class<?>[] parameterTypes) {
        Method method = getMethod(obj.getClass(), methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] with parameter types:"
                    + ObjectUtil.toPrettyString(parameterTypes) + " on class [" + obj.getClass() + ']');
        }
        return invokeMethod(obj, method, args);
    }

    /**
     * 反射调用对象方法, 无视 private/protected 修饰符.
     *
     * 只匹配函数名，如果有多个同名函数调用第一个. 用于确信只有一个同名函数, 但参数类型不确定的情况.
     *
     * 性能较低，仅用于单次调用.
     *
     * @param obj        目标对象
     * @param methodName 方法名
     * @param args       方法参数
     * @param <T>        返回值类型
     * @return 方法的返回值
     */
    public static <T> T invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
        Method method = getAccessibleMethodByName(obj.getClass(), methodName);
        if (method == null) {
            throw new IllegalArgumentException(
                    "Could not find method [" + methodName + "] on class [" + obj.getClass() + ']');
        }
        return invokeMethod(obj, method, args);
    }

    /**
     * 反射调用对象方法, 无视 private/protected 修饰符.
     *
     * @param obj     目标对象
     * @param method  方法
     * @param args    方法参数
     * @param <T>     返回值类型
     * @return 方法的返回值
     */
    public static <T> T invokeMethod(final Object obj, final Method method, final Object... args) {
        try {
            method.setAccessible(true);
            return (T) method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke method: " + method.getName(), e);
        }
    }

    /**
     * 调用构造函数.
     *
     * @param cls  目标类
     * @param args 构造函数参数
     * @param <T>  返回值类型
     * @return 构造函数的返回值
     * @throws RuntimeException 如果无法调用构造函数
     */
    public static <T> T invokeConstructor(final Class<T> cls, Object... args) {
        try {
            return ConstructorUtils.invokeConstructor(cls, args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke constructor for class " + cls.getName(), e);
        }
    }

    /**
     * 改变 private/protected 的方法为可访问，尽量不进行改变，避免 JDK 的 SecurityManager 抱怨。
     *
     * @param method 要改变可访问性的方法
     */
    public static void makeAccessible(Method method) {
        if (!method.canAccess(null)
                && (!Modifier.isPublic(method.getModifiers())
                        || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))) {
            method.setAccessible(true);
        }
    }

    /**
     * 改变 private/protected 的成员变量为可访问，尽量不进行改变，避免 JDK 的 SecurityManager 抱怨。
     *
     * @param field 要改变可访问性的成员变量
     */
    public static void makeAccessible(Field field) {
        if (!field.canAccess(null)
                && (!Modifier.isPublic(field.getModifiers())
                        || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                        || Modifier.isFinal(field.getModifiers()))) {
            field.setAccessible(true);
        }
    }
}
