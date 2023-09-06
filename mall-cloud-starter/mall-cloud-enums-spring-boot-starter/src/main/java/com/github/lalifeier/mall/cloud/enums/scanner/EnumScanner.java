package com.github.lalifeier.mall.cloud.enums.scanner;

import com.github.lalifeier.mall.cloud.enums.annocation.EnumInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class EnumScanner extends ClassPathBeanDefinitionScanner {

    private final List<Class<?>> enumClasses = new ArrayList<>();

    public EnumScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AssignableTypeFilter(EnumInfo.class));
    }

    public List<Class<?>> getEnumClasses() {
        return enumClasses;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            Class<?> enumClass = getClass(holder.getBeanDefinition().getBeanClassName());
            enumClasses.add(enumClass);
        }
        return beanDefinitionHolders;
    }

    private Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class: " + className, e);
        }
    }
}
