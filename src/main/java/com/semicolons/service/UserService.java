package com.semicolons.service;

import com.semicolons.dto.DeviceRegistrationDTO;
import com.semicolons.entity.User;
import com.semicolons.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerOrGetDevice(DeviceRegistrationDTO dto) {
        UUID uuid = UUID.fromString(dto.getDeviceUuid());
        return userRepository.findById(uuid)
                .orElseGet(() -> userRepository.save(new User(uuid)));
    }

    public Optional<User> getUserByDeviceUuid(String deviceUuid) {
        return userRepository.findById(UUID.fromString(deviceUuid));
    }

    public Optional<User> updateUserByDeviceUuid(String deviceUuid, User updatedUser) {
        UUID uuid = UUID.fromString(deviceUuid);
        return userRepository.findById(uuid).map(user -> {
            user.setName(updatedUser.getName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setGender(updatedUser.getGender());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setAddress(updatedUser.getAddress());
            if (updatedUser.isCustomCodesProvided()) {
                user.setCustomCodes(updatedUser.getCustomCodes());
            }
            return userRepository.save(user);
        });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<String> getCustomCodes(String deviceUuid) {
        UUID uuid = UUID.fromString(deviceUuid);
        User user = userRepository.findById(uuid)
                .orElseGet(() -> userRepository.save(new User(uuid)));
        return user.getCustomCodes();
    }

    @Transactional
    public List<String> appendCustomCodes(String deviceUuid, List<String> newWords) {
        UUID uuid = UUID.fromString(deviceUuid);
        User user = userRepository.findById(uuid)
                .orElseGet(() -> userRepository.save(new User(uuid)));

        List<String> currentCodes = user.getCustomCodes();
        for (String word : newWords) {
            String trimmed = word.trim().toLowerCase();
            if (!trimmed.isEmpty() && !currentCodes.contains(trimmed)) {
                currentCodes.add(trimmed);
            }
        }
        user.setCustomCodes(currentCodes);
        userRepository.save(user);
        return user.getCustomCodes();
    }
}
