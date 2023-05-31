package com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity;

//@Data
//public class UserPrincipal implements UserDetails {
//
//    private Long id;
//
//    private String username;
//
//    private String password;
//    private Boolean enabled;
//    private Collection<SimpleGrantedAuthority> authorities;
//
//    public UserPrincipal(User user) {
//        //this.setId(user.getId());
//        this.setUsername(user.getUsername());
//        this.setPassword(user.getPassword());
//        this.setEnabled(user.getEnabled());
//        if (user.getRoles() != null) {
//            authorities = new ArrayList<>();
//            user.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
//        }
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//}
