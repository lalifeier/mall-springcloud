package com.github.lalifeier.mall.cloud.enums.context;

import com.github.lalifeier.mall.cloud.enums.scanner.EnumScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class EnumContextFactory {
    private static EnumContext enumContext;

    public static EnumContext getEnumContext() {
        if (enumContext == null) {
            throw new IllegalStateException("EnumContext has not been created. Please call createEnumContext() first.");
        }
        return enumContext;
    }

    public static EnumContext createEnumContext(String basePackage, BeanDefinitionRegistry registry) {
        EnumScanner enumScanner = new EnumScanner(registry);
        enumScanner.scan(basePackage);

        EnumContext enumContext = new EnumContext();
        for (Class<?> enumClass : enumScanner.getEnumClasses()) {
            String enumClassName = enumClass.getSimpleName();
            enumContext.registerEnum(enumClassName, enumClass);
        }

        return enumContext;
    }
}
