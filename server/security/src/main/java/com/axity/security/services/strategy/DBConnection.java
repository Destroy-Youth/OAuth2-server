package com.axity.security.services.strategy;
import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.*;
import com.axity.security.services.facade.impl.LoginFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBConnection implements ConnectionStrategy {
    @Autowired
    UserDAO userDAO;

    LoginFacade loginFacade= new LoginFacade();
    @Override
    public void saveUser(String name, String password, int age) {
        UserDO user=new UserDO(name,password,age);
        this.userDAO.save(user);
    }
    @Override
    public UserTO login(UserTO userTO){
        return this.loginFacade.login(userTO);
    }
}
