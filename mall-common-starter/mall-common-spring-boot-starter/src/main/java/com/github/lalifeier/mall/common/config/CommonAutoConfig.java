package com.github.lalifeier.mall.common.config;

import com.github.lalifeier.mall.common.advice.GlobalExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonAutoConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean({GlobalExceptionAdvice.class})
    public GlobalExceptionAdvice exceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login");
//    }

    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.
//                addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
//                .resourceChain(false);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/swagger-ui/")
//                .setViewName("forward:/swagger-ui/index.html");
//    }
}

