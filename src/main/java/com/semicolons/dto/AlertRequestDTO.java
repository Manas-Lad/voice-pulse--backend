package com.semicolons.dto;

import java.util.List;

public class AlertRequestDTO {
    private String deviceUuid;
    private Long userId;
    private Long timestamp;
    private Double score;
    private List<String> signals;
    private Double latitude;
    private Double longitude;
    private Float locationAccuracy;

    public AlertRequestDTO() {}

    public String getDeviceUuid() { return deviceUuid; }
    public void setDeviceUuid(String deviceUuid) { this.deviceUuid = deviceUuid; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public List<String> getSignals() { return signals; }
    public void setSignals(List<String> signals) { this.signals = signals; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public Float getLocationAccuracy() { return locationAccuracy; }
    public void setLocationAccuracy(Float locationAccuracy) { this.locationAccuracy = locationAccuracy; }
}
