package com.semicolons.service;

import org.springframework.stereotype.Service;

import com.semicolons.dto.UserRequestDTO;
import com.semicolons.repository.UserRepository;
import com.semicolons.entity.User;

import java.util.List;
import  java.util.Optional;

@Service 
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRequestDTO request) {
        User user = new User(request.getName(), request.getPhoneNumeber());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
