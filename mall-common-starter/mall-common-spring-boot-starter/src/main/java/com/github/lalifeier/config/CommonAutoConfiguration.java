package com.github.lalifeier.config;

import com.github.lalifeier.advice.GlobalExceptionAdvice;
import com.github.lalifeier.advice.GlobalResponseAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({GlobalExceptionAdvice.class})
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean({GlobalResponseAdvice.class})
    public GlobalResponseAdvice globalResponseAdvice() {
        return new GlobalResponseAdvice();
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

