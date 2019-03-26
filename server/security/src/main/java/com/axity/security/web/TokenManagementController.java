package com.axity.security.web;

import com.axity.security.services.facade.impl.AuthorizationFacade;
import com.axity.security.services.jwt.impl.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/man")
public class TokenManagementController {
    static final Logger LOG = LogManager.getLogger(TestController.class);

    @Autowired
    private AuthorizationFacade authorizationFacade;

    @RequestMapping(value = "/validate",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity validate(@RequestHeader Map<String, String> headers) {

        return authorizationFacade.validatePetition(headers);
    }

}
