package com.axity.security.services.service;


import com.axity.security.model.UserDO;

import java.util.Optional;

public interface ILoginService{
    UserDO login (String name, String password);
}
