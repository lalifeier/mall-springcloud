package com.github.lalifeier.mall.security.interceptor;

import com.github.lalifeier.mall.security.constant.SecurityConstant;
import com.github.lalifeier.mall.security.properties.SecurityProperties;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServerProtectInterceptor implements HandlerInterceptor {

    private SecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {

        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }

        String token = request.getHeader(SecurityConstant.GATEWAY_TOKEN_HEADER);

        String gatewayToken = new String(Base64Utils.encode(SecurityConstant.GATEWAY_TOKEN_VALUE.getBytes()));

        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
//            ResultData<String> resultData = new ResultData<>();
//            resultData.setSuccess(false);
//            resultData.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            resultData.setMessage("请通过网关访问资源");
//            WebUtils.writeJson(response, resultData);
            return false;
        }
    }

    public void setProperties(SecurityProperties properties) {
        this.properties = properties;
    }
}
