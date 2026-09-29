package com.semicolons.service;

import com.semicolons.dto.AlertRequestDTO;
import com.semicolons.entity.Alert;
import com.semicolons.repository.AlertRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert saveAlert(AlertRequestDTO request) {
        Alert alert = new Alert(
            request.getDeviceUuid(),
            request.getUserId(),
            request.getScore(),
            request.getSignals(),
            request.getTimestamp(),
            request.getLatitude(),
            request.getLongitude(),
            request.getLocationAccuracy()
        );
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }

    public List<Alert> getAlertsByUserId(Long userId) {
        return alertRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    public List<Alert> getAlertsByDeviceUuid(String deviceUuid) {
        return alertRepository.findByDeviceUuidOrderByTimestampDesc(deviceUuid);
    }

    public Optional<Alert> getAlertById(UUID id) {
        return alertRepository.findById(id);
    }

    public Optional<Alert> getSharedAlert(String token) {
        return alertRepository.findByShareToken(token)
                .filter(alert -> alert.getShareExpiresAt() != null
                        && alert.getShareExpiresAt().isAfter(LocalDateTime.now()));
    }
}