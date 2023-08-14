package com.github.lalifeier.mall.cloud.auth.infrastructure.security.login.sms;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.converter.Converter;

public class SmsAuthenticationConverter
        implements Converter<HttpServletRequest, SmsAuthenticationToken> {

    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";

    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";

    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;

    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;

    @Override
    public SmsAuthenticationToken convert(HttpServletRequest request) {
        String phone = request.getParameter(phoneParameter);
        phone = (phone != null) ? phone.trim() : "";
        String code = request.getParameter(codeParameter);
        code = (code != null) ? code : "";

        return SmsAuthenticationToken.unauthenticated(phone, code);
    }
}
