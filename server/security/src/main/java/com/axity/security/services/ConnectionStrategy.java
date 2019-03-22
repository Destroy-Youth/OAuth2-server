package com.axity.security.services;

import com.axity.security.commons.to.UserTO;

public interface ConnectionStrategy{
    void saveUser(String name,String password,int age);
    void login(UserTO userTO);
}
