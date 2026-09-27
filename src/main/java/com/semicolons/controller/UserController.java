package com.semicolons.controller;

import org.springframework.transaction.annotation.Transactional;

import com.semicolons.dto.DeviceRegistrationDTO;
import com.semicolons.entity.User;
import com.semicolons.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-device")
    public ResponseEntity<User> registerDevice(@RequestBody DeviceRegistrationDTO dto) {
        User user = userService.registerOrGetDevice(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/device/{deviceUuid}")
    public ResponseEntity<User> getUserByDevice(@PathVariable String deviceUuid) {
        return userService.getUserByDeviceUuid(deviceUuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/device/{deviceUuid}")
    public ResponseEntity<User> updateProfileByDevice(@PathVariable String deviceUuid, @RequestBody User request) {
        return userService.updateUserByDeviceUuid(deviceUuid, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/device/{deviceUuid}")
    @Transactional
    public ResponseEntity<?> unregisterDevice(@PathVariable String deviceUuid) {
        return userRepository.findByDeviceUuid(deviceUuid).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}