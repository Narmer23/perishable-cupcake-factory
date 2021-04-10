package it.project.cupcakefactory.service;

import it.project.cupcakefactory.dto.UserDto;
import it.project.cupcakefactory.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(UserDto userDto);

    UserDto getUserByEmail(String email);
}
