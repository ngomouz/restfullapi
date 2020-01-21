package com.grentechs.cogigroup.services;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
