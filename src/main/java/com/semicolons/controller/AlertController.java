package com.semicolons.controller;

import com.semicolons.dto.AlertRequestDTO;
import com.semicolons.entity.Alert;
import com.semicolons.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createAlert(@RequestBody AlertRequestDTO request) {
        Alert createdAlert = alertService.saveAlert(request);
        return new ResponseEntity<>(Map.of("alert", createdAlert, "shareToken", createdAlert.getShareToken()), HttpStatus.CREATED);
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

    @GetMapping("/shared/{token}")
    public ResponseEntity<Map<String, Object>> getSharedAlert(@PathVariable String token) {
        return alertService.getSharedAlert(token)
                .map(alert -> {
                    Map<String, Object> details = new HashMap<>();
                    details.put("id", alert.getId());
                    details.put("signals", alert.getSignals());
                    details.put("timestamp", alert.getTimestamp());
                    details.put("status", alert.getStatus());
                    details.put("score", alert.getScore());
                    details.put("latitude", alert.getLatitude());
                    details.put("longitude", alert.getLongitude());
                    details.put("locationAccuracy", alert.getLocationAccuracy());
                    return ResponseEntity.ok(details);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
