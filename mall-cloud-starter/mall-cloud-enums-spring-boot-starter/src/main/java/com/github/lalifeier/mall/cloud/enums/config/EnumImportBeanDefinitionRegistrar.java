package com.github.lalifeier.mall.cloud.enums.config;

import com.github.lalifeier.mall.cloud.enums.context.EnumContextFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class EnumImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {
    EnumContextFactory.createEnumContext("com.example.enums", registry);

    // EnumScanner enumScanner = new EnumScanner(registry);
    // enumScanner.scan("com.example.enums"); // 替换为你实际的枚举类所在的包路径
    //
    // for (Class<?> enumClass : enumScanner.getEnumClasses()) {
    // String enumClassName = enumClass.getSimpleName();
    // RootBeanDefinition beanDefinition = new RootBeanDefinition(enumClass);
    // registry.registerBeanDefinition(enumClassName, beanDefinition);
    // }
  }
}
