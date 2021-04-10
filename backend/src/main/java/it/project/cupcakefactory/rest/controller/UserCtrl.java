package it.project.cupcakefactory.rest.controller;

import it.project.cupcakefactory.dto.UserDto;
import it.project.cupcakefactory.repository.UserRepository;
import it.project.cupcakefactory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
public class UserCtrl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserDto getUser(@PathVariable UUID id) {
        return new UserDto(userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserDto addUser(@RequestBody UserDto userDto) {
        return new UserDto(userService.createUser(userDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
    }
}
