package com.github.lalifeier.mall.cloud.common.constant;

public final class SecurityConstants {

    private final String LOGIN_URL = "/login";
    public static final String AUTHORITY_PREFIX = "ROLE_";

    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    public static final String GRANT_TYPE_SMS_CODE = "sms_code";

    public static final String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";

    public static final String AUTH_SIGNING_KEY = "sign";
    public static final String JWT_HEADER_KEY = "Authorization";

    public static final String BEARER_TYPE = "Bearer";

    public static final String TOKEN_BLACKLIST_PREFIX = "InvalidToken";
}
