package com.axity.security.services.facade;

import com.axity.security.commons.to.UserTO;
import com.axity.security.model.UserDO;

import java.util.Optional;

public interface ILoginFacade{
    Optional<UserDO> login(UserTO userTO);
}
