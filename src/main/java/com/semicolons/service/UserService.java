package com.semicolons.service;

import com.semicolons.dto.UserRequestDTO;
import com.semicolons.entity.User;
import com.semicolons.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRequestDTO request) {
        User user = new User(request.getName(), request.getPhoneNumber());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setGender(updatedUser.getGender());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setAddress(updatedUser.getAddress());
            return userRepository.save(user);
        });
    }
}