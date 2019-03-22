package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.UserTO;
import com.axity.security.services.ConnectionStrategy;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.service.impl.LoginService;
import com.axity.security.services.strategy.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class LoginFacade implements ILoginFacade{
    @Autowired
    LoginService loginService;
    @Autowired
    ConnectionStrategy connectionStrategy;
    private ModelMapper modelMapper= new ModelMapper();

    @Override
    public void login(UserTO userTO) {
        int strategy=1;
        if(strategy==1){
            this.connectionStrategy.authentication(userTO);
        }
    }
}
