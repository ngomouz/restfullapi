package com.grentechs.cogigroup.services;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUserById(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
