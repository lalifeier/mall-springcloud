package com.github.lalifeier.mall.cloud.common.utils;

import com.github.lalifeier.mall.cloud.common.annocation.EnumValue;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * 实用工具类，用于处理枚举类型
 */
@Slf4j
public class EnumUtil {
    public EnumUtil() {}

    /**
     * 检查目标类型是否为枚举类型
     *
     * @param targetType 目标类型
     * @throws IllegalArgumentException 如果目标类型不是枚举类型
     */
    public static void isEnumType(Class<?> targetType) {
        if (null == targetType || !targetType.isEnum()) {
            throw new IllegalArgumentException(
                    "The target type " + targetType.getName() + " does not refer to an enum");
        }
    }

    /**
     * 根据代码值获取枚举项的描述
     *
     * @param enumClass 枚举类型
     * @param code 代码值
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 枚举项的描述
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> String getDescriptionByCode(Class<E> enumClass, T code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> code.equals(e.getCode()))
                .findFirst()
                .map(BaseEnum::getDescription)
                .orElse(null);
    }

    /**
     * 根据代码值获取枚举项
     *
     * @param enumClass 枚举类型
     * @param code 代码值
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 对应的枚举项
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnumByCode(Class<E> enumClass, T code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> code.equals(e.getCode()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据名称获取枚举项
     *
     * @param enumClass 枚举类型
     * @param name 名称
     * @param <E> 枚举类型
     * @return 对应的枚举项
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnum(Class<E> enumClass, String name) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> name.equals(e.name()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据不区分大小写的名称获取枚举项
     *
     * @param enumClass 枚举类型
     * @param name 不区分大小写的名称
     * @param <E> 枚举类型
     * @return 对应的枚举项
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> E getEnumIgnoreCase(Class<E> enumClass, String name) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> name.equalsIgnoreCase(e.name()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取枚举项的代码值列表
     *
     * @param enumClass 枚举类型
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 代码值列表
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> List<T> getCodeList(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(BaseEnum::getCode)
                .collect(Collectors.toList());
    }

    /**
     * 获取枚举项的描述列表
     *
     * @param enumClass 枚举类型
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 描述列表
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> List<String> getDescriptionList(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(BaseEnum::getDescription)
                .collect(Collectors.toList());
    }

    /**
     * 获取枚举项的列表
     *
     * @param enumClass 枚举类型
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 枚举项的列表
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> List<E> getEnumList(Class<E> enumClass) {
        return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
    }

    /**
     * 将枚举转换为 Map，其中键为代码值，值为描述
     *
     * @param enumClass 枚举类型
     * @param <E> 枚举类型
     * @param <T> 代码值类型
     * @return 枚举转换后的 Map
     */
    public static <E extends Enum<E> & BaseEnum<E, T>, T> Map<T, String> getEnumMap(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(BaseEnum::getCode, BaseEnum::getDescription));
    }

    /**
     * 获取枚举类中用于表示字段名的属性名称。
     *
     * @param enumClass 枚举类
     * @return 用于表示字段名的属性名称
     * @throws IllegalArgumentException 如果在枚举类中找不到适当的属性
     */
    public static String getEnumFieldName(Class<?> enumClass) {
        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            return BaseEnum.code;
        } else {
            return getEnumAnnotationField(enumClass)
                    .map(Field::getName)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("Could not find appropriate property in Class: %s.", enumClass.getName())));
        }
    }

    /**
     * 获取枚举类中带有 @EnumValue 注解的字段。
     *
     * @param enumClass 枚举类
     * @return 带有 @EnumValue 注解的字段
     */
    public static Optional<Field> getEnumAnnotationField(Class<?> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(EnumValue.class))
                .findFirst();
    }

    /**
     * 获取枚举类中指定字段的类型。
     *
     * @param enumClass  枚举类
     * @param fieldName 字段名
     * @return 字段的类型，如果字段不存在则返回 null
     */
    public static Class<?> getEnumFieldType(Class<?> enumClass, String fieldName) {
        try {
            Field field = enumClass.getDeclaredField(fieldName);
            return field.getType();
        } catch (NoSuchFieldException e) {
            log.error("Failed to get field type", e);
            return null;
        }
    }

    /**
     * 获取枚举类中指定字段的值。
     *
     * @param enumConstant  枚举实例
     * @param fieldName 字段名
     * @return 字段的值，如果字段不存在或无法访问则返回 null
     */
    public static Object getEnumFieldValue(Object enumConstant, String fieldName) {
        try {
            Field field = enumConstant.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(enumConstant);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Failed to get field value", e);
        }
        return null;
    }

    //    private static boolean isMatch(Object enumConstant, String value) {
    //        if (enumConstant instanceof BaseEnum) {
    //            return ((BaseEnum<?, ?>) enumConstant).getCode().equals(value);
    //        }
    //
    //        Optional<Field> field = EnumUtil.getEnumAnnotationField(enumConstant.getClass());
    //        if (field.isPresent()) {
    //            try {
    //                Field enumField = field.get();
    //                Object fieldValue = enumField.get(enumConstant);
    //                return fieldValue.equals(value);
    //            } catch (Exception e) {
    //                return false;
    //            }
    //        }
    //
    //        return enumConstant.equals(value);
    //    }
    //
    //    public static Class<?> getEnum(Class<?> enumClass, String value) {
    //        Object[] enumConstants = enumClass.getEnumConstants();
    //
    //        for (Object enumConstant : enumConstants) {
    //            if (isMatch(enumConstant, value)) {
    //                return enumConstant.getClass();
    //            }
    //        }
    //
    //        return null;
    //    }
    //
    //    public static Object getEnumValue(Class<?> enumClass) {
    //        if (BaseEnum.class.isAssignableFrom(enumClass)) {}
    //
    //        Optional<Field> field = EnumUtil.getEnumAnnotationField(enumClass.getClass());
    //        if (field.isPresent()) {
    //            try {
    //            } catch (Exception e) {
    //            }
    //        }
    //    }
}
