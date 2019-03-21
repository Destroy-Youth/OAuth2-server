package com.axity.security.persistence;

import com.axity.security.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserDAO extends CrudRepository<UserDO,Long>{

    Optional<UserDO> findByNameAndPassword(String name, String password);

}
