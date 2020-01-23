package com.grentechs.cogigroup.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.entities.Views;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/jsonviews")
public class UserJsonViewController {

    private final UserService userService;

    public UserJsonViewController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(value = Views.External.class)
    @GetMapping(path = "/external/getUserById/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @JsonView(value = Views.Internal.class)
    @GetMapping(path = "/internal/findUserById/{id}")
    public Optional<User> findUserById(@PathVariable(value = "id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
