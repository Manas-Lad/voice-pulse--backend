package com.semicolons.entity;

import com.semicolons.util.StringListConverter;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    @Column(nullable = true)
    private Float locationAccuracy;

    @Column(nullable = true, unique = true, length = 36)
    private String shareToken;

    @Column(nullable = true)
    private LocalDateTime shareExpiresAt;

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

    public Alert(String deviceUuid, Long userId, Double score, List<String> signals, Long epochMillis,
                 Double latitude, Double longitude, Float locationAccuracy) {
        this.deviceUuid = deviceUuid;
        this.userId = userId;
        this.score = score;
        this.signals = (signals != null) ? signals : new ArrayList<>();
        this.status = "TRIGGERED";
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationAccuracy = locationAccuracy;
        this.shareToken = UUID.randomUUID().toString();
        this.shareExpiresAt = LocalDateTime.now().plusHours(24);
        
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
    @JsonIgnore
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    @JsonIgnore
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    @JsonIgnore
    public Float getLocationAccuracy() { return locationAccuracy; }
    public void setLocationAccuracy(Float locationAccuracy) { this.locationAccuracy = locationAccuracy; }
    @JsonIgnore
    public String getShareToken() { return shareToken; }
    @JsonIgnore
    public LocalDateTime getShareExpiresAt() { return shareExpiresAt; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public List<String> getSignals() { return signals; }
    public void setSignals(List<String> signals) { this.signals = signals; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
