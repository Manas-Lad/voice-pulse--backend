package com.semicolons.entity;

import com.semicolons.util.StringListConverter;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long userId;

    @Column(nullable = true)
    private String deviceUuid;

    @Column(nullable = false)
    private Double score;

    @Convert(converter = StringListConverter.class)
    @Column(name = "signals")
    private List<String> signals = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String status;

    public Alert() {}

    public Alert(Long userId, Double score, List<String> signals, Long epochMillis) {
        this.userId = userId;
        this.score = score;
        this.signals = (signals != null) ? signals : new ArrayList<>();
        this.status = "TRIGGERED";
        
        if (epochMillis != null && epochMillis > 0) {
            this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        } else {
            this.timestamp = LocalDateTime.now();
        }
    }

    public Alert(String deviceUuid, Long userId, Double score, List<String> signals, Long epochMillis) {
        this.deviceUuid = deviceUuid;
        this.userId = userId;
        this.score = score;
        this.signals = (signals != null) ? signals : new ArrayList<>();
        this.status = "TRIGGERED";
        
        if (epochMillis != null && epochMillis > 0) {
            this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        } else {
            this.timestamp = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getDeviceUuid() { return deviceUuid; }
    public void setDeviceUuid(String deviceUuid) { this.deviceUuid = deviceUuid; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public List<String> getSignals() { return signals; }
    public void setSignals(List<String> signals) { this.signals = signals; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}