package com.semicolons.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private boolean emergencyAlerts = true;
    private boolean locationSharing = false;

    public Contact() {}

    public Contact(String name, String phone, boolean emergencyAlerts, boolean locationSharing) {
        this.name = name;
        this.phone = phone;
        this.emergencyAlerts = emergencyAlerts;
        this.locationSharing = locationSharing;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isEmergencyAlerts() { return emergencyAlerts; }
    public void setEmergencyAlerts(boolean emergencyAlerts) { this.emergencyAlerts = emergencyAlerts; }
    public boolean isLocationSharing() { return locationSharing; }
    public void setLocationSharing(boolean locationSharing) { this.locationSharing = locationSharing; }
}