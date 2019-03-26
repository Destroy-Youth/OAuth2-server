package com.axity.security.web;

import com.axity.security.commons.to.TokenTO;
import com.axity.security.commons.to.UserTO;
import com.axity.security.services.jwt.impl.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    static final Logger LOG = LogManager.getLogger(TestController.class);

    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity createToken(@RequestBody UserTO user) {

        TokenTO tokenTO = new TokenTO("");
        String token = tokenService.createToken(user.getName(),5000);
        tokenTO.setToken(token);

        return new ResponseEntity<>(tokenTO,HttpStatus.CREATED);
    }

}
