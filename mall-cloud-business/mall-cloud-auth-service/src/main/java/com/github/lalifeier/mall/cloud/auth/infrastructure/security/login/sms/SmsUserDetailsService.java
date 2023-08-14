package com.github.lalifeier.mall.cloud.auth.infrastructure.security.login.sms;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SmsUserDetailsService {
    UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException;
}
