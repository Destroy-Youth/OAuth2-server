package com.axity.security.services.strategy;
import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.*;
import com.axity.security.services.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DBConnection implements ConnectionStrategy {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserDAO userDAO;

    public DBConnection() {
    }

    @Override
    public void saveUser(String name, String password, int age) {
        UserDO user=new UserDO(name,password,age);
        this.userDAO.save(user);
    }

    @Override
    public UserDO authentication(UserTO userTO) {
        return this.loginService.getInfo(userTO.getName(),userTO.getPassword());
    }

}
