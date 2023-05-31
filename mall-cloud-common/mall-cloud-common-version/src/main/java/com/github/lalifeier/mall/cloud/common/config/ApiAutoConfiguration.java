package com.github.lalifeier.mall.cloud.common.config;

import com.github.lalifeier.mall.cloud.common.apiversion.ApiHandlerMapping;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@RequiredArgsConstructor
public class ApiAutoConfiguration implements WebMvcRegistrations {

    @Override
    @NonNull
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiHandlerMapping();
    }
}

