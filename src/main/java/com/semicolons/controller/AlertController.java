package com.semicolons.controller;

import com.semicolons.dto.AlertRequestDTO;
import com.semicolons.entity.Alert;
import com.semicolons.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody AlertRequestDTO request) {
        Alert createdAlert = alertService.saveAlert(request);
        return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Alert>> getAlertsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getAlertsByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}