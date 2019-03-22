package com.axity.security;

import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.ConnectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{

    @Autowired
    ConnectionStrategy conexion;
    @Autowired
    UserDAO prueba;
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        //conexion.saveUser("evanny","rivera",3);
        UserDO byNameAndPassword = prueba.findByNameAndPassword("ejjeej", "rivera").get();
    }
}
