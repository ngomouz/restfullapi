package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(path = "/createUser")
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/getUserById/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(path = "/updateUserById/{id}")
    public User updateUserById(@PathVariable(value = "id", required = true) Long id, @RequestBody @Valid User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping(path = "/deleteUserById/{id}")
    public void deleteUserById(@PathVariable(value = "id", required = true) Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping(path = "/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable(value = "username", required = true) String username) {
        return userService.getUserByUsername(username);
    }
}
