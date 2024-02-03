package com.czarnecki.clinicservicesystem.user;

import java.util.Set;

public class Role {

    private String code;
    private String name;
    private Set<Authority> authorities;

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
