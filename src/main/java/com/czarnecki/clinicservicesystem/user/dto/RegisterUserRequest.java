package com.czarnecki.clinicservicesystem.user.dto;

import javax.validation.constraints.NotBlank;

public class RegisterUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String role;

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
