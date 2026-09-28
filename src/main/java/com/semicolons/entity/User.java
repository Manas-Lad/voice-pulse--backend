package com.semicolons.entity;

import com.semicolons.util.StringListConverter;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

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

    public User() {}

    public User(UUID id) {
        this.id = id;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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

    public void setCustomCodes(List<String> customCodes) {
        this.customCodes = customCodes;
    }
}