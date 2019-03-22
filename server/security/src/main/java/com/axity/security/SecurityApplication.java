package com.axity.security;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.ConnectionStrategy;
import com.axity.security.services.facade.impl.LoginFacade;
import com.axity.security.services.service.impl.LoginService;
import com.axity.security.services.strategy.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{


    @Autowired
    LoginFacade prueba;

	public static void main(String[] args) {
	    SpringApplication.run(SecurityApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {

        //conexion.saveUser("iban","asdda",3);
        //conexion.saveUser("evanny","rivera",3);
        UserTO userTO=new UserTO();
        userTO.setName("evanny");
        userTO.setPassword("rivera");
        this.prueba.login(userTO);
    }
}
