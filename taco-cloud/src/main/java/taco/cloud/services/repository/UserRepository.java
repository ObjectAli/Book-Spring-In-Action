package taco.cloud.services.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import taco.cloud.pojo.User;

@Repository
public class UserRepository //extends CrudRepository<User, Long>
{

    @Autowired
    PasswordEncoder encoder;

    public User findByUsername(String username){

        if (username.equals("tacochief"))
            return new User(123L, "tacochief", encoder.encode("password"), "ROLE_ADMIN");

        else return null;
    }
}