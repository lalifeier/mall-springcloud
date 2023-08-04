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
    // List<User> findUserList = userList.stream().filter(item ->
    // item.getUsername().equals(username)).collect(Collectors.toList());
    // if (CollectionUtils.isEmpty(findUserList)) {
    //    throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
    // }
    //
    // UserPrincipal userPrincipal = new UserPrincipal(findUserList.get(0));
    // if (!userPrincipal.isEnabled()) {
    //    throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
    // } else if (!userPrincipal.isAccountNonLocked()) {
    //    throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
    // } else if (!userPrincipal.isAccountNonExpired()) {
    //    throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
    // } else if (!userPrincipal.isCredentialsNonExpired()) {
    //    throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
    // }
    // return userPrincipal;

    UserPrincipal userPrincipal = userRepository.findUserByUsername(username);
    if (userPrincipal == null) {
      throw new UsernameNotFoundException("用户名或密码为空");
    }

    return userPrincipal;
  }
}
