package com.github.lalifeier.mall.cloud.auth.infrastructure.security.login.password;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.converter.Converter;

public class PasswordAuthenticationConverter
        implements Converter<HttpServletRequest, PasswordAuthenticationToken> {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";

    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    @Override
    public PasswordAuthenticationToken convert(HttpServletRequest request) {
        String username = request.getParameter(usernameParameter);
        username = (username != null) ? username.trim() : "";
        String password = request.getParameter(passwordParameter);
        password = (password != null) ? password : "";

        return PasswordAuthenticationToken.unauthenticated(username, password);
    }
}
