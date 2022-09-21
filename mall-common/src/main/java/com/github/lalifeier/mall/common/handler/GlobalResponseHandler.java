package com.github.lalifeier.mall.common.handler;

import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice()
public class GlobalResponseHandler implements ResponseBodyAdvice<Object>  {
//    private final String basePackagePattern;
//    private final AntPathMatcher matcher = new AntPathMatcher(".");
//
//    public GlobalResponseHandler(String basePackage) {
//        this.basePackagePattern = basePackage + ".**";
//    }

    public boolean supports(MethodParameter returnType, Class converterType) {
        String returnTypeName = returnType.getDeclaringClass().getName();
        log.debug(returnTypeName);
//        return matcher.match(basePackagePattern,clazz.getName());
        return  true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if(body == null) {
            return  Response.buildSuccess();
        }

        if (body instanceof Response) {
            return body;
        }

        return SingleResponse.of(body);
    }
}
