package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginFacade implements ILoginFacade{
    @Autowired
    LoginService loginService;
    @Override
    public Optional<UserDO> login(UserTO userTO) {

        Optional<UserDO> login = this.loginService.login(userTO.getName(), userTO.getPassword());
        return login;
    }
}
