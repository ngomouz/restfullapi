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
@RequestMapping(path = "/versioning/uri/users")
public class UserUriVersioningController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserUriVersioningController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = {"/v1.0/{id}", "/v1.1/{id}"})
    public UserDtoV1 findUserByIdV1(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userService.getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
        User user = optionalUser.get();
        return userMapper.userToUserDtoV1(user);
    }

    @GetMapping(path = "/v2.0/{id}")
    public UserDtoV2 findUserByIdV2(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userService.getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
        User user = optionalUser.get();
        return userMapper.userToUserDtoV2(user);
    }
}
