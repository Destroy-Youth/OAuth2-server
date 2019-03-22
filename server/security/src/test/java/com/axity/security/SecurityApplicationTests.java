package com.axity.security;

import com.axity.security.services.jwt.ITokenCreation;
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
	public void validateToken(){
		String token = tokenService.createToken("Ivan",300);
		boolean verified = tokenService.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiSXZhbiIsImlhdCI6MTU1MzI5Mzk1NH0.VJKBsHzu3exsaQi9thAFycWXr_pq4VqySAzDFiQMWFI");
		Assert.assertTrue(verified);
	}

}
