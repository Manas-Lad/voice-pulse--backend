package com.semicolons.dto;

public class DeviceRegistrationDTO {
    private String deviceUuid;

    public DeviceRegistrationDTO() {}

    public DeviceRegistrationDTO(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getDeviceUuid() { return deviceUuid; }
    public void setDeviceUuid(String deviceUuid) { this.deviceUuid = deviceUuid; }
}