package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserExistsException;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, UriComponentsBuilder builder) {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/getUserById/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (UserExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping(path = "/getUserById/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping(path = "/updateUserById/{id}")
    public User updateUserById(@PathVariable(value = "id", required = true) Long id, @RequestBody @Valid User user) {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping(path = "/deleteUserById/{id}")
    public void deleteUserById(@PathVariable(value = "id", required = true) Long id) {
        try {
            userService.deleteUserById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(path = "/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable(value = "username", required = true) String username) {
        try {
            return userService.getUserByUsername(username);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
