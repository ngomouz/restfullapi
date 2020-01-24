package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.dtos.UserMmDto;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(path = "/modelmapper/users")
public class UserModelMapperController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserModelMapperController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/getUserById/{id}")
    public UserMmDto getUserById(@PathVariable(value = "id") @Min(1) Long id) throws UserNotFoundException {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            if (!optionalUser.isPresent()) {
                throw new UserNotFoundException("USER DOES NOT EXIST");
            }
            User user = optionalUser.get();
            return modelMapper.map(user, UserMmDto.class);
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException("USER DOES NOT EXIST");
        }
    }
}
