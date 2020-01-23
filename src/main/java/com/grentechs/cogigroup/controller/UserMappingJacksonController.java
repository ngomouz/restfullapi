package com.grentechs.cogigroup.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Validated
@RestController
@RequestMapping(path = "/jacksonfilter/users")
public class UserMappingJacksonController {

    private final UserService userService;

    public UserMappingJacksonController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/getUserById/{id}")
    public MappingJacksonValue getUserById(@PathVariable(value = "id") @Min(1) Long id) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();
            Set<String> fields = new HashSet<>();
            fields.add("userId");
            fields.add("username");
            fields.add("orders");
            fields.add("ssn");
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter( "userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(path = "/findUserById/{id}")
    public MappingJacksonValue findUserById(@PathVariable(value = "id") @Min(1) Long id, @RequestParam(value = "fields") Set<String> fields) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter( "userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
