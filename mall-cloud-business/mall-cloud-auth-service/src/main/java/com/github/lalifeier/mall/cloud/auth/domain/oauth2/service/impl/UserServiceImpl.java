package com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.impl;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.repository.UserRepository;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = userRepository.findByUsername(username);
        if (userPrincipal == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        return new User(
                userPrincipal.getUsername(),
                userPrincipal.getPassword(),
                true,
                true,
                true,
                true,
                authorityList);
    }
}
