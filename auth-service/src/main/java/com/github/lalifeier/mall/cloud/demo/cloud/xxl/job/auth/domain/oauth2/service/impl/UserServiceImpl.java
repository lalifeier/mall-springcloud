package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.oauth2.service.impl;

import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.oauth2.service.UserService;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.user.model.entity.UserDO;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.user.repository.UserRepository;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.infrastructure.constant.Message;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    //private List<User> userList;
    //private final PasswordEncoder passwordEncoder;

    //@PostConstruct
    //public void initData() {
    //    String password = passwordEncoder.encode("123456");
    //    userList = new ArrayList<>();
    //    userList.add(new User(1L, "admin", password, 1, Arrays.asList("ADMIN")));
    //    userList.add(new User(2L, "user", password, 1, Arrays.asList("USER")));
    //}


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //List<User> findUserList = userList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        //if (CollectionUtils.isEmpty(findUserList)) {
        //    throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        //}
        //
        //UserPrincipal userPrincipal = new UserPrincipal(findUserList.get(0));
        //if (!userPrincipal.isEnabled()) {
        //    throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        //} else if (!userPrincipal.isAccountNonLocked()) {
        //    throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        //} else if (!userPrincipal.isAccountNonExpired()) {
        //    throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        //} else if (!userPrincipal.isCredentialsNonExpired()) {
        //    throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        //}
        //return userPrincipal;


        UserDO user = userRepository.findUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(Message.USERNAME_PASSWORD_ERROR);
        }


        return null;
    }
}
