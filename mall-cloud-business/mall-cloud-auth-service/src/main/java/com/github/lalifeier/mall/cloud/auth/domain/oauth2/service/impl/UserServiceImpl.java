package com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.impl;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.repository.UserRepository;
import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    return userPrincipal;
  }
}
