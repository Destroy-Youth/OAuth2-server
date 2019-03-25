package com.axity.security.services.strategy;
import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;
import com.axity.security.services.*;
import org.springframework.stereotype.Component;

@Component
public class JiraConnection implements ConnectionStrategy{

    @Override
    public void saveUser(String name, String password, int age) {

    }

    @Override
    public UserDO authentication(UserTO userTO) {
        return null;
    }

}
