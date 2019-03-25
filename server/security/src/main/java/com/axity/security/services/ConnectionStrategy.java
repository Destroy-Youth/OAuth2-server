package com.axity.security.services;

import com.axity.security.model.UserDO;
import com.axity.security.commons.to.UserTO;
import org.springframework.stereotype.Component;

@Component
public interface ConnectionStrategy{
    void saveUser(String name,String password,int age);
    UserDO authentication(UserTO userTO);
}
