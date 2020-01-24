package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserExistsException;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.exceptions.UsernameNotFoundException;
import com.grentechs.cogigroup.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/users")
@Api(tags = "User Management Service RESTful Services", value = "UserController")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retrieve list of all Users")
    @GetMapping(path = "/getAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @ApiOperation(value = "Create a new User")
    @PostMapping(path = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/getUserById/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (UserExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @ApiOperation(value = "Retrieve a User by his Id")
    @GetMapping(path = "/getUserById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User getUserById(@PathVariable(value = "id") @Min(1) Long id) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            if(!optionalUser.isPresent()) {
                throw new UserNotFoundException("USER NOT FOUND");
            }
            return optionalUser.get();
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @ApiOperation(value = "Update a User by his Id")
    @PutMapping(path = "/updateUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUserById(@PathVariable(value = "id", required = true) Long id, @RequestBody @Valid User user) {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @ApiOperation(value = "Delete a User by his Id")
    @DeleteMapping(path = "/deleteUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserById(@PathVariable(value = "id", required = true) Long id) {
        try {
            userService.deleteUserById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @ApiOperation(value = "Get a User by his Username")
    @GetMapping(path = "/getUserByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByUsername(@PathVariable(value = "username", required = true) String username) throws UsernameNotFoundException {
        User user =  userService.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("USER WITH USERNAME '"+username.toUpperCase()+"' NOT EXISTS");
        }
        return user;
    }
}
