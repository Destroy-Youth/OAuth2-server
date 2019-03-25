package com.axity.security;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.facade.impl.LoginFacade;
import com.axity.security.services.service.impl.LoginService;
import com.axity.security.services.strategy.DBConnection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	@Autowired
	LoginService loginService;
	@Autowired
	DBConnection dbConnection;
	@Test
	public void contextLoads() {
	}
	@Test
	public void mustBeSuccess(){
		dbConnection.saveUser("evanny","ieee",23);
		UserDO userDO =loginService.getInfo("evanny","ieee");
		Assert.assertNotNull(userDO);
	}
}
