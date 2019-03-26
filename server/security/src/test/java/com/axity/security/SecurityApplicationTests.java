package com.axity.security;

import com.axity.security.services.jwt.impl.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	TokenService tokenService;

	@Test
	public void createToken(){
		String token = tokenService.createToken("Ivan",3000);
		Assert.assertNotNull(token);
	}

	@Test
    public void validateCreatedToken(){
		String token = tokenService.createToken("Franz",300);
		boolean verified = tokenService.verifyToken(token);
		Assert.assertTrue(verified);
    }


	@Test
	public void validateNotCreatedToken(){
		String token = tokenService.createToken("Franz",300);
		tokenService.deleteToken(token);
		Boolean verified = tokenService.verifyToken(token);
		Assert.assertFalse(verified);
	}

}
