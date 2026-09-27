package com.semicolons.service;

import com.semicolons.dto.DeviceRegistrationDTO;
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

    public User registerOrGetDevice(DeviceRegistrationDTO dto) {
        return userRepository.findByDeviceUuid(dto.getDeviceUuid())
                .orElseGet(() -> userRepository.save(new User(dto.getDeviceUuid())));
    }

    public Optional<User> getUserByDeviceUuid(String deviceUuid) {
        return userRepository.findByDeviceUuid(deviceUuid);
    }

    public Optional<User> updateUserByDeviceUuid(String deviceUuid, User updatedUser) {
        return userRepository.findByDeviceUuid(deviceUuid).map(user -> {
            user.setName(updatedUser.getName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setGender(updatedUser.getGender());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setAddress(updatedUser.getAddress());
            return userRepository.save(user);
        });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public boolean deleteByDeviceUuid(String deviceUuid) {
    return userRepository.findByDeviceUuid(deviceUuid).map(user -> {
        userRepository.delete(user);
        return true;
    }).orElse(false);
}

}