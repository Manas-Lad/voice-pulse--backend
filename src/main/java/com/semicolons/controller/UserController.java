package com.semicolons.controller;

import com.semicolons.dto.DeviceRegistrationDTO;
import com.semicolons.entity.User;
import com.semicolons.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS
})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-device")
    public ResponseEntity<User> registerDevice(@RequestBody DeviceRegistrationDTO dto) {
        return ResponseEntity.ok(userService.registerOrGetDevice(dto));
    }

    @GetMapping("/device/{deviceUuid}")
    public ResponseEntity<User> getUserByDevice(@PathVariable String deviceUuid) {
        return userService.getUserByDeviceUuid(deviceUuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/device/{deviceUuid}")
    public ResponseEntity<User> updateUser(@PathVariable String deviceUuid, @RequestBody User user) {
        return userService.updateUserByDeviceUuid(deviceUuid, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/device/{deviceUuid}/codes")
    public ResponseEntity<List<String>> getCustomCodes(@PathVariable String deviceUuid) {
        return ResponseEntity.ok(userService.getCustomCodes(deviceUuid));
    }

    @PostMapping("/device/{deviceUuid}/codes")
    public ResponseEntity<List<String>> appendCustomCodes(
            @PathVariable String deviceUuid,
            @RequestBody List<String> codes) {
        return ResponseEntity.ok(userService.appendCustomCodes(deviceUuid, codes));
    }
}