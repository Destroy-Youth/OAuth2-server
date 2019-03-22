package com.axity.security.services.strategy;
import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.persistence.UserDAO;
import com.axity.security.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DBConnection implements ConnectionStrategy {
    @Autowired
    UserDAO userDAO;
    public DBConnection(){}
    @Override
    public void saveUser(String name, String password, int age) {
        UserDO user=new UserDO(name,password,age);
        this.userDAO.save(user);
    }

    @Override
    public void authentication(UserTO userTO) {
        UserDO userDO= this.userDAO.findByNameAndPassword(userTO.getName(),userTO.getPassword());
        System.out.println(userDO.getId());
        System.out.println(userDO.getName());
        System.out.println(userDO.getPassword());
        System.out.println(userDO.getAge());
    }

}
