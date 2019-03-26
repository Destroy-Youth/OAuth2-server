package com.axity.security.web;

import java.io.Console;

import com.axity.security.commons.to.UserTO;
import com.axity.security.commons.to.TokenTO;
import com.axity.security.services.facade.impl.LoginFacade;

import com.axity.security.services.facade.ILoginFacade;

import com.axity.security.services.jwt.impl.TokenService;
import com.axity.security.services.strategy.DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	static final Logger LOG = LogManager.getLogger(LoginController.class);

	@Autowired

	LoginFacade loginFacade;
	@Autowired
    TokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TokenTO> login(@RequestBody UserTO user) {
		UserTO loggedTO=this.loginFacade.login(user);
		String tokenLogin=this.tokenService.createToken(loggedTO.getName(),60);
		TokenTO tokenTO= new TokenTO(tokenLogin);
		LOG.info(user);
		return new ResponseEntity<>(tokenTO,HttpStatus.OK);
	}
}