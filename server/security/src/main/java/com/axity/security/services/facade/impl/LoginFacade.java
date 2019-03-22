package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;

public class LoginFacade implements ILoginFacade{
    @Autowired
    LoginService loginService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserTO login(UserTO userTO) {

        UserDO loginDO = this.loginService.login(userTO.getName(), userTO.getPassword());
        UserTO loginTO = this.modelMapper.map(loginDO,UserTO.class);
        return loginTO;
    }
}
