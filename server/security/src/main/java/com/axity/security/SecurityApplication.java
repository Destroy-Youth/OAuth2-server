package com.axity.security;

import com.axity.security.model.UserDO;
import com.axity.security.services.ConnectionStrategy;
import com.axity.security.services.impl.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{

    @Autowired
    ConnectionStrategy conexion;
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        conexion.saveUser("iban","asdda",3);
    }
}
