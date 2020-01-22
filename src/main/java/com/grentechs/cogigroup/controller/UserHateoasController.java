package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.Order;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.repositories.UserRepository;
import com.grentechs.cogigroup.services.UserService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(path = "/hateoas/users")
public class UserHateoasController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserHateoasController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/getUserById/{userId}")
    public Optional<User> getUserById(@PathVariable(value = "userId") Long userId) {
        try {
            return userService.getUserById(userId);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(path = "/findUserById/{userId}")
    public Resource<User> findUserById(@PathVariable(value = "userId") @Min(1) Long userId) {
        try {
            Optional<User> optionalUser = userService.getUserById(userId);
            if(!optionalUser.isPresent()) {
                System.out.println("not null");
            }
            User user = optionalUser.get();
            Long optionaluserId = user.getUserId();
            Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash("findUserById/"+optionaluserId).withSelfRel();
            user.add(selfLink);
            Resource<User> finalResource = new Resource<>(user);
            return finalResource;
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping(path = "/findAllUsers")
    public Resources<User> findAllUsers() throws UserNotFoundException {
        try {
            List<User> users = userService.getAllUser();
            users.forEach(user -> {
                Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash("findUserById/"+user.getUserId()).withSelfRel();
                user.add(selfLink);
                try {
                    Resources<Order> orderResources = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getUserId());
                    Link orderLinks = ControllerLinkBuilder.linkTo(orderResources).withRel("all-orders");
                    user.add(orderLinks);
                } catch (UserNotFoundException e) {
                    e.getMessage();
                }
            });
            Link selfLinkGetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).slash("findAllUsers").withSelfRel();
            return new Resources<>(users, selfLinkGetAllUsers);
        } catch (Exception exception) {
            throw new UserNotFoundException("USER NOT FOUND !");
        }
    }

    @GetMapping(path = "/getAllOrders/{userId}/orders")
    public Resources<Order> getAllOreders(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        user.get().getOrders().forEach(order -> {
            Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash("getAllOreders/"+userId+"/orders").withSelfRel();
            order.add(selfLink);
        });

        return new Resources<>(user.get().getOrders());
    }
}
