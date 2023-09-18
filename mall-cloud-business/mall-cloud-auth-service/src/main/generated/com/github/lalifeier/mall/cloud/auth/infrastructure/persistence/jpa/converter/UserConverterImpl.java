package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.converter;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T14:55:52+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (BellSoft)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPrincipal toData(UserPO userPO) {
        if ( userPO == null ) {
            return null;
        }

        UserPrincipal userPrincipal = new UserPrincipal();

        userPrincipal.setId( userPO.getId() );
        userPrincipal.setUsername( userPO.getUsername() );
        userPrincipal.setEmail( userPO.getEmail() );
        userPrincipal.setPhone( userPO.getPhone() );
        userPrincipal.setPassword( userPO.getPassword() );

        return userPrincipal;
    }
}
