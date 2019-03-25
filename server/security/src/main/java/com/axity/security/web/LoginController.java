package com.axity.security.web;

import java.io.Console;

import com.axity.security.commons.to.UserTO;
import com.axity.security.services.facade.ILoginFacade;
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
    ILoginFacade iLoginFacade;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UserTO> login(@RequestBody UserTO user) {	
		this.iLoginFacade.login(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}