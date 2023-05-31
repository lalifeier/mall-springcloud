package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.component;

//import com.github.lalifeier.mall.auth.domain.user.model.entity.UserPrincipal;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class JwtTokenEnhancer implements TokenEnhancer {
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//        Map<String, Object> info = new HashMap<>();
//        // 把用户ID设置到JWT中
//        info.put("id", userPrincipal.getId());
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
//        return accessToken;
//    }
//}
