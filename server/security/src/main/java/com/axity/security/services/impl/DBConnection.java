package com.axity.security.services.impl;
import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBConnection implements ConnectionStrategy {
    @Autowired
    UserDAO userDAO;
    @Override
    public void saveUser(String name, String password, int age) {
        UserDO user=new UserDO(name,password,age);
        this.userDAO.save(user);
    }
}
