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

        // T typedCode;
        // if (enumClass.getEnumConstants()[0].getCode().getClass().isInstance(code)) {
        // typedCode = (T) enumClass.getEnumConstants()[0].getCode().getClass().cast(code);
        // } else if (enumClass.getEnumConstants()[0].getCode() instanceof Integer
        // && code instanceof String) {
        // typedCode = (T) Integer.valueOf((String) code);
        // } else {
        // throw new IllegalArgumentException("Invalid code type: " + code.getClass().getName());
        // }

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> String.valueOf(e.getCode()).equalsIgnoreCase(String.valueOf(code)))
                .findFirst()
                .orElse(null);
    }
}
