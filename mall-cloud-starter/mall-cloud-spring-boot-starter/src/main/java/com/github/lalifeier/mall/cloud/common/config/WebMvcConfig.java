package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.aspect.WebLogAspect;
import com.github.lalifeier.mall.cloud.common.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @Import({
// JacksonConfig.class,
// })
public class WebMvcConfig implements WebMvcConfigurer {

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor()
    // .addPathPatterns("/**");
    // }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //        registry.addConverterFactory(new IntegerToEnumConverterFactory());
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
}
