package com.czarnecki.clinicservicesystem.auth;

import javax.validation.constraints.NotBlank;

class AuthenticationRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public AuthenticationRequest() {
    }

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
