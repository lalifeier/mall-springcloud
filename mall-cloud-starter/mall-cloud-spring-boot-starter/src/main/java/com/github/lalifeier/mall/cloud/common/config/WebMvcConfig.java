package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.aspect.WebLogAspect;
import com.github.lalifeier.mall.cloud.common.converter.DateConverter;
import com.github.lalifeier.mall.cloud.common.converter.IntegerToEnumConverterFactory;
import com.github.lalifeier.mall.cloud.common.converter.LocalDateConverter;
import com.github.lalifeier.mall.cloud.common.converter.LocalDateTimeConverter;
import com.github.lalifeier.mall.cloud.common.converter.LocalTimeConverter;
import com.github.lalifeier.mall.cloud.common.converter.StringToEnumConverterFactory;
import com.github.lalifeier.mall.cloud.common.event.DomainEventPublisher;
import com.github.lalifeier.mall.cloud.common.event.impl.DomainEventPublisherImpl;
import com.github.lalifeier.mall.cloud.common.filter.WrapRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor()
    // .addPathPatterns("/**");
    // }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());

        registry.addConverter(new DateConverter());
        registry.addConverter(new LocalDateConverter());
        registry.addConverter(new LocalTimeConverter());
        registry.addConverter(new LocalDateTimeConverter());
    }

    @Bean
    // @ConditionalOnProperty(value = "log.enabled", havingValue = "true")
    public WebLogAspect apiLogAspect() {
        return new WebLogAspect();
    }

    @Bean
    public WrapRequestFilter wrapRequestFilter() {
        return new WrapRequestFilter();
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new DomainEventPublisherImpl();
    }
}
