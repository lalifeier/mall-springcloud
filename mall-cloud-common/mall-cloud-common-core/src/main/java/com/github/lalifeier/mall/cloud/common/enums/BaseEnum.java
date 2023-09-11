package com.github.lalifeier.mall.cloud.common.enums;

import java.util.Arrays;

// @JsonSerialize(using = BaseEnumSerializer.class)
public interface BaseEnum<E extends Enum<E>, T> {

    public static String code = "code";

    public static String description = "description";

    // @JsonValue
    T getCode();

    String getDescription();

    static <E extends Enum<E> & BaseEnum<E, T>, T> E parse(Class<E> enumClass, Object code) {
        if (code == null || code.toString().isEmpty()) {
            return null;
        }

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> String.valueOf(e.getCode()).equalsIgnoreCase(String.valueOf(code)))
                .findFirst()
                .orElse(null);
    }

    //    default Class<?> getPropertyType() {
    //        try {
    //            Field codeField = this.getClass().getField(code);
    //            return codeField.getType();
    //        } catch (NoSuchFieldException e) {
    //            return null;
    //        }
    //    }
}
