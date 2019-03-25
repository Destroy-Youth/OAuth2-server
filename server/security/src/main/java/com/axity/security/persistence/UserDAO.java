package com.axity.security.persistence;

import com.axity.security.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
public interface UserDAO extends CrudRepository<UserDO,Long>{

    Optional<UserDO> findByNameAndPassword(String name,String password);

}
