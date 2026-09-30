package com.semicolons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @EmbeddedId
    private ContactId id = new ContactId();

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_contacts_user"))
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, insertable = false, updatable = false)
    private String phone;

    private boolean emergencyAlerts = true;
    private boolean locationSharing = false;

    public Contact() {}

    public Contact(String name, String phone, boolean emergencyAlerts, boolean locationSharing) {
        this.name = name;
        setPhone(phone);
        this.emergencyAlerts = emergencyAlerts;
        this.locationSharing = locationSharing;
    }

    @JsonIgnore
    public ContactId getId() { return id; }
    public void setId(ContactId id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) {
        this.user = user;
        if (id == null) id = new ContactId();
        id.setUserId(user == null ? null : user.getId());
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone != null ? phone : (id == null ? null : id.getPhone()); }
    public void setPhone(String phone) {
        this.phone = phone;
        if (id == null) id = new ContactId();
        id.setPhone(phone);
    }

    public boolean isEmergencyAlerts() { return emergencyAlerts; }
    public void setEmergencyAlerts(boolean emergencyAlerts) { this.emergencyAlerts = emergencyAlerts; }
    public boolean isLocationSharing() { return locationSharing; }
    public void setLocationSharing(boolean locationSharing) { this.locationSharing = locationSharing; }
}
