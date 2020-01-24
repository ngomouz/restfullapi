package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.dtos.UserMsDto;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.mappers.UserMapper;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mapstruct/users")
public class UserMapStructController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserMapStructController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping(path = "/getAllUsersDtos")
    public List<UserMsDto> getAllUsersDtos() {
        return userMapper.usersToUserMsDtos(userService.getAllUser());
    }

    @GetMapping(path = "/findUserMsById/{id}")
    public UserMsDto findUserMsById(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = this.userService.getUserById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
        User user = optionalUser.get();
        return userMapper.userToUserMsDto(user);
    }
}
