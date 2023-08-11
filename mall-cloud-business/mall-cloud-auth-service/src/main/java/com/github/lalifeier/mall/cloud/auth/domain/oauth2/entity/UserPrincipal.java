package com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
