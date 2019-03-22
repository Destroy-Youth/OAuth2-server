package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginFacade implements ILoginFacade{
    @Autowired
    LoginService loginService;

    private ModelMapper modelMapper= new ModelMapper();

    @Override
    public UserTO login(UserTO userTO) {
        try {
            UserDO loginDO = this.loginService.login(userTO.getName(),userTO.getPassword());
            UserTO loginTO = this.modelMapper.map(loginDO, UserTO.class);
            return loginTO;
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
}
