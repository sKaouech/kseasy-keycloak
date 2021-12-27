package com.dealtobook.event;

public class UserDto {

    private UserAttributesDto attributes;
    private String firstName;
    private String email;

    public UserDto(UserAttributesDto attributes, String firstName, String email) {
        this.attributes = attributes;
        this.firstName = firstName;
        this.email = email;
    }

    public UserAttributesDto getAttributes() {
        return attributes;
    }

    public void setAttributes(UserAttributesDto attributes) {
        this.attributes = attributes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}