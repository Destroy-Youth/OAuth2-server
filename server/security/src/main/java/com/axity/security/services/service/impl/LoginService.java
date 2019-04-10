package com.axity.security.services.service.impl;

import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService implements ILoginService{
    @Autowired
    private UserDAO userDAO;
    public LoginService() {
    }
    @Override
    public void validateToken() {
    }
    @Override
    public UserDO getInfo(String name, String password) {
        return this.userDAO.findByNameAndPassword(name,password).get();
    }

}
