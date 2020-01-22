package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.Order;
import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.OrderNotFoundException;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
import com.grentechs.cogigroup.repositories.OrderRepository;
import com.grentechs.cogigroup.repositories.UserRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(path = "/users")
public class OrderController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/getAllOreders/{userId}/orders")
    public List<Order> getAllOreders(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        return new ArrayList<>(user.get().getOrders());
    }

    @PostMapping(path = "/createOrder/{userId}/orders")
    public void createOrder(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Order order) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        order.setUser(user.get());
        orderRepository.save(order);
    }

    @GetMapping(path = "/getOrderByOrderId/{userId}/{orderId}")
    public Order getOrderByOrderId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "orderId") Long orderId) throws UserNotFoundException, OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        if(!order.isPresent()) {
            throw new OrderNotFoundException("THIS ORDER ID IS NOT FOUND");
        }
        List<Order> orderlist = user.get().getOrders().stream().filter(ordervalue -> ordervalue.getId().equals(orderId)).collect(Collectors.toList());

        if(orderlist.size() == 1) {
            return orderlist.get(0);
        }
        throw new OrderNotFoundException("THIS ORDER ID IS NOT FOUND");
    }
}
