package com.semicolons.service;

import com.semicolons.dto.AlertRequestDTO;
import com.semicolons.entity.Alert;
import com.semicolons.repository.AlertRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert saveAlert(AlertRequestDTO request) {
        Alert alert = new Alert(
            request.getUserId(),
            request.getScore(),
            request.getSignals(),
            request.getTimestamp()
        );
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }

    public List<Alert> getAlertsByUserId(Long userId) {
        return alertRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }
}