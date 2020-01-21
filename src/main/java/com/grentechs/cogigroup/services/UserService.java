package com.grentechs.cogigroup.services;

import com.grentechs.cogigroup.entities.User;
import com.grentechs.cogigroup.exceptions.UserExistsException;
import com.grentechs.cogigroup.exceptions.UserNotFoundException;
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

    public User createUser(User user) throws UserExistsException {
        User userfromdb = userRepository.findByUsername(user.getUsername());
        if(userfromdb != null) {
            throw new UserExistsException("USER ALREADY EXISTS");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User>  user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }

        return user;
    }

    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        userRepository.delete(user.get());
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        return user;
    }
}
