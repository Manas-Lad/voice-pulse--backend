package com.semicolons.dto;

import com.semicolons.entity.User;

public class UserRequestDTO {
    private String name;
    private String phoneNumber;

    public UserRequestDTO() {

    }

    public UserRequestDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumeber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
