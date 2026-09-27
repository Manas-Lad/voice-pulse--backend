package com.semicolons.dto;

import java.util.List;

public class AlertRequestDTO {
    private Long userId;
    private Long timestamp;
    private Double score;
    private List<String> signals;

    public AlertRequestDTO() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public List<String> getSignals() { return signals; }
    public void setSignals(List<String> signals) { this.signals = signals; }
}