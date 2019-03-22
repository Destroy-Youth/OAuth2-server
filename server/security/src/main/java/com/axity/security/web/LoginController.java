package com.axity.security.web;

import java.io.Console;

import com.axity.model.User;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public String login(@RequestBody User user) {		
		System.out.println(user.name);
		System.out.println(user.pwd);
		return "Hello "+user.name+user.pwd+"!!";
	}
}