package com.axity.security.services.service.impl;

import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService{
    @Autowired
    UserDAO userDAO;

    @Override
    public Optional<UserDO> login(String name,String password) {

        Optional<UserDO> byNameAndPassword = this.userDAO.findByNameAndPassword(name, password);
        return byNameAndPassword;
    }
}
