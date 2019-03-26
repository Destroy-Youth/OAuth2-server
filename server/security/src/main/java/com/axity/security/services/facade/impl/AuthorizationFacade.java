package com.axity.security.services.facade.impl;

import com.axity.security.services.facade.IAuthorizationFacade;
import com.axity.security.services.jwt.impl.TokenService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorizationFacade implements IAuthorizationFacade {

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity validatePetition(Map<String, String> headers) {
        String token = tokenService.extractHeaderToken(headers);
        ResponseEntity response;

        if (tokenService.verifyToken(token)){
            response = new ResponseEntity<>(HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return response;

    }
}
