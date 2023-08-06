package com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;
//import com.github.lalifeier.mall.cloud.common.model.result.Result;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Override
//  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//    Result result = Result.failure(HttpErrorCodeEnum.UNAUTHORIZED, exception.getMessage());
//
//    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//    response.getWriter().write(objectMapper.writeValueAsString(result));
//    response.getWriter().flush();
//  }
//}
