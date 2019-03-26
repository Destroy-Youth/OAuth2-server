package com.axity.security.services.facade.impl;

import com.axity.security.commons.to.TokenTO;
import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.ConnectionStrategy;
import com.axity.security.services.facade.ILoginFacade;
import com.axity.security.services.jwt.impl.TokenService;
import com.axity.security.services.service.impl.LoginService;
import com.axity.security.services.strategy.DBConnection;
import com.axity.security.services.strategy.JiraConnection;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;


@Service
public class LoginFacade implements ILoginFacade{

    @Autowired
    DBConnection dbConnection;
    @Autowired
    TokenService tokenService;
    @Override
    public TokenTO login(UserTO userTO) {
        int strategy=1;
        if(strategy==1){

            UserDO loggedDO = this.dbConnection.authentication(userTO);
            String token = this.tokenService.createToken(loggedDO.getName(),60);
            TokenTO tokenTO= new TokenTO(token);
            return tokenTO;
        }else{
            return null;
        }

    }
}
