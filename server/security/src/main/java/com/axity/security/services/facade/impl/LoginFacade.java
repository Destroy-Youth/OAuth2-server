package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.ConnectionStrategy;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.service.impl.LoginService;
import com.axity.security.services.strategy.DBConnection;
import com.axity.security.services.strategy.JiraConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;


@Service
public class LoginFacade implements ILoginFacade{

    private ModelMapper modelMapper= new ModelMapper();
    @Autowired
    DBConnection dbConnection;

    @Override
    public UserTO login(UserTO userTO) {
        int strategy=1;
        if(strategy==1){

            UserDO loggedDO = this.dbConnection.authentication(userTO);
            UserTO loggedTO= this.modelMapper.map(loggedDO,UserTO.class);
            return loggedTO;
        }else{
            return null;
        }

    }
}
