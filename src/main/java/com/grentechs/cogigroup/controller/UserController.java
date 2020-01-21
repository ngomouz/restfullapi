package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
