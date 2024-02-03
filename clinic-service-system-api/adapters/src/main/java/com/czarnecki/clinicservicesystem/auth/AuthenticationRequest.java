package com.czarnecki.clinicservicesystem.auth;

import jakarta.validation.constraints.NotBlank;

class AuthenticationRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


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
}
