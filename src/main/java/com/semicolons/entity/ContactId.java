package com.semicolons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ContactId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "phone", nullable = false)
    private String phone;

    public ContactId() {}

    public ContactId(UUID userId, String phone) {
        this.userId = userId;
        this.phone = phone;
    }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ContactId)) return false;
        ContactId that = (ContactId) other;
        return Objects.equals(userId, that.userId) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, phone);
    }
}
