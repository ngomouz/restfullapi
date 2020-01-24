package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.dtos.UserDtoV1;
import com.grentechs.cogigroup.dtos.UserDtoV2;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.mappers.UserMapper;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(path = "/versioning/params/users")
public class UserCustomHearderVersioningController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserCustomHearderVersioningController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/{id}", headers = "API-VERSION=1")
    public UserDtoV1 findUserByIdV1(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userService.getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
        User user = optionalUser.get();
        return userMapper.userToUserDtoV1(user);
    }

    @GetMapping(path = "/{id}", headers = "API-VERSION=2")
    public UserDtoV2 findUserByIdV2(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userService.getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
        User user = optionalUser.get();
        return userMapper.userToUserDtoV2(user);
    }
}
