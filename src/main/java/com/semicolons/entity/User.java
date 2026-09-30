package com.semicolons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.semicolons.util.StringListConverter;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Persistable<UUID> {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private String name;
    private String phoneNumber;
    private String gender;
    private String dateOfBirth;
    private String address;

    @Convert(converter = StringListConverter.class)
    @Column(name = "custom_codes", columnDefinition = "text")
    private List<String> customCodes = new ArrayList<>();

    @Transient
    @JsonIgnore
    private boolean isNew = false;

    @Transient
    @JsonIgnore
    private boolean customCodesProvided = false;

    public User() {}

    public User(UUID id) {
        this.id = id;
        this.isNew = true; // Signals Spring Data to INSERT, not UPDATE
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return this.isNew || this.name == null;
    }

    @PostLoad
    @PostPersist
    void markNotNew() {
        this.isNew = false;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<String> getCustomCodes() {
        if (this.customCodes == null) {
            this.customCodes = new ArrayList<>();
        }
        return customCodes;
    }

    @JsonSetter("customCodes")
    public void setCustomCodes(List<String> customCodes) {
        this.customCodes = (customCodes != null) ? customCodes : new ArrayList<>();
        this.customCodesProvided = true;
    }

    @JsonIgnore
    public boolean isCustomCodesProvided() {
        return customCodesProvided;
    }
}
