package com.github.lalifeier.mall.cloud.auth.infrastructure.security.login.password;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/login", "POST");

    private Converter<HttpServletRequest, PasswordAuthenticationToken> passwordAuthenticationConverterConverter;

    private boolean postOnly = true;

    public PasswordAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        passwordAuthenticationConverterConverter = new PasswordAuthenticationConverter();
    }

    public PasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        passwordAuthenticationConverterConverter = new PasswordAuthenticationConverter();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        PasswordAuthenticationToken passwordAuthenticationToken =
                passwordAuthenticationConverterConverter.convert(request);

        setDetails(request, passwordAuthenticationToken);

        return this.getAuthenticationManager().authenticate(passwordAuthenticationToken);
    }

    protected void setDetails(HttpServletRequest request, PasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
