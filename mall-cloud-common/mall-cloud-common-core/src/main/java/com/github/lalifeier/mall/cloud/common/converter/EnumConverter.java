package com.github.lalifeier.mall.cloud.common.converter;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import com.github.lalifeier.mall.cloud.common.utils.EnumUtil;
import com.github.lalifeier.mall.cloud.common.utils.reflect.ClassUtil;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumConverter {

    // public static Enum<?> convert(Class<?> enumClass, String enumValue) {
    // if (!ClassUtil.isEnum(enumClass)) {
    // return null;
    // }
    // if (enumValue == null || enumValue.toString().isEmpty()) {
    // return null;
    // }
    //
    // if (BaseEnum.class.isAssignableFrom(enumClass)) {
    // return Arrays.stream((BaseEnum<?, ?>[]) enumClass.getEnumConstants())
    // .filter(e -> enumValue.equals(((BaseEnum<?, ?>) e).getCode())).map(e -> (Enum<?>) e)
    // .findFirst().orElse(null);
    // }
    //
    // Optional<Field> field = getEnumAnnotationField(enumClass);
    // if (field.isPresent()) {
    // try {
    // field.get().setAccessible(true);
    // Object fieldValue = field.get().get(null);
    // if (fieldValue != null && fieldValue.toString().equals(enumValue)) {
    // return (Enum<?>) fieldValue;
    // }
    // } catch (IllegalAccessException e) {
    // log.error("Error accessing enum field", e);
    // }
    // return null;
    // }
    //
    // return Arrays.stream((Enum<?>[]) enumClass.getEnumConstants())
    // .filter(e -> e.name().equals(enumValue)).findFirst().orElse(null);
    // }

    public static <T extends Enum<?>> T convert(Class<T> enumClass, Object enumValue) {
        if (!ClassUtil.isEnum(enumClass)) {
            return null;
        }
        if (enumValue == null || enumValue.toString().isEmpty()) {
            return null;
        }

        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            return Arrays.stream((BaseEnum<?, ?>[]) enumClass.getEnumConstants())
                    .filter(e -> String.valueOf(enumValue).equals(String.valueOf(e.getCode())))
                    .map(e -> (T) e)
                    .findFirst()
                    .orElse(null);
        }

        Optional<Field> field = EnumUtil.getAnnotationField(enumClass);
        if (field.isPresent()) {
            try {
                field.get().setAccessible(true);
                Object fieldValue = field.get().get(null);
                if (fieldValue != null && fieldValue.toString().equals(enumValue)) {
                    return (T) fieldValue;
                }
            } catch (IllegalAccessException e) {
                log.error("Error accessing enum field", e);
            }
            return null;
        }

        return Arrays.stream((T[]) enumClass.getEnumConstants())
                .filter(e -> e.name().equals(enumValue))
                .findFirst()
                .orElse(null);
    }

    public static <T> T convert(Object obj) {
        if (!ClassUtil.isEnum(obj)) {
            return null;
        }

        if (BaseEnum.class.isAssignableFrom(obj.getClass())) {
            return ((BaseEnum<?, T>) obj).getCode();
        }

        Optional<Field> field = EnumUtil.getAnnotationField(obj.getClass());
        if (field.isPresent()) {
            try {
                field.get().setAccessible(true);
                return (T) field.get().get(obj);
            } catch (IllegalAccessException e) {
                log.error("Error accessing enum field", e);
            }
        }

        return (T) ((Enum<?>) obj).name();
    }

    public Class<?> getPropertyType(Object obj) {
        if (!ClassUtil.isEnum(obj)) {
            return null;
        }

        if (obj instanceof BaseEnum) {
            BaseEnum<?, ?> baseEnum = (BaseEnum<?, ?>) obj;
            return baseEnum.getCode().getClass();
        }

        Optional<Field> field = EnumUtil.getAnnotationField(obj.getClass());
        if (field.isPresent()) {

            field.get().setAccessible(true);
            return field.get().getType();
        }

        return null;
    }
}
