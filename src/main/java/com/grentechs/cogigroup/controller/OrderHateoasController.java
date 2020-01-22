package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.Order;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.OrderNotFoundException;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.repositories.OrderRepository;
import com.grentechs.cogigroup.repositories.UserRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/hateoas/users")
public class OrderHateoasController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderHateoasController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/getAllOrders/{userId}")
    public Resources<Order> getAllOrders(@PathVariable(value = "userId", required = true) Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND !");
        }
        return new Resources<>(user.get().getOrders());
    }

    @GetMapping(path = "/getOrderByOrderId/{userId}/{orderId}")
    public Resource<Order> getOrderByOrderId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "orderId") Long orderId) throws UserNotFoundException, OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        if(!order.isPresent()) {
            throw new OrderNotFoundException("THIS ORDER ID IS NOT FOUND");
        }
        List<Order> orderlist = user.get().getOrders().stream().filter(ordervalue -> ordervalue.getOrderId().equals(orderId)).collect(Collectors.toList());

        if(orderlist.size() == 1) {
            Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash("getOrderByOrderId/"+userId+"/"+orderId).withSelfRel();
            orderlist.get(0).add(selfLink);
            return new Resource<>(orderlist.get(0));
        }
        throw new OrderNotFoundException("THIS ORDER ID IS NOT FOUND");
    }
}
