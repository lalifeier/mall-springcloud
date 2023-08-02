package com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.impl;

//@AllArgsConstructor
//@Service
//public class UserServiceImpl implements UserService {
//
//  private final UserRepository userRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    // List<User> findUserList = userList.stream().filter(item ->
//    // item.getUsername().equals(username)).collect(Collectors.toList());
//    // if (CollectionUtils.isEmpty(findUserList)) {
//    //    throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
//    // }
//    //
//    // UserPrincipal userPrincipal = new UserPrincipal(findUserList.get(0));
//    // if (!userPrincipal.isEnabled()) {
//    //    throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
//    // } else if (!userPrincipal.isAccountNonLocked()) {
//    //    throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
//    // } else if (!userPrincipal.isAccountNonExpired()) {
//    //    throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
//    // } else if (!userPrincipal.isCredentialsNonExpired()) {
//    //    throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
//    // }
//    // return userPrincipal;
//
//    User user = userRepository.findUserByUsername(username);
//    if (ObjectUtils.isEmpty(user)) {
//      throw new UsernameNotFoundException(LoginErrorCodeEnum.B_USER_PASSWORD_ERROR.getMessage());
//    }
//
//    return null;
//  }
//}
