package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.DomainEntity;
import com.czarnecki.clinicservicesystem.user.vo.RoleSnapshot;
import java.util.Set;
import java.util.stream.Collectors;

public class Role implements DomainEntity<String, RoleSnapshot> {

    static Role from(RoleSnapshot snapshot) {
        return new Role(snapshot.code(), snapshot.name(),
            snapshot.authorities()
                .stream()
                .map(Authority::from)
                .collect(Collectors.toSet()));
    }

    private String code;
    private String name;
    private Set<Authority> authorities;

    private Role(String code, String name, Set<Authority> authorities) {
        this.code = code;
        this.name = name;
        this.authorities = authorities;
    }

    @Override
    public RoleSnapshot getSnapshot() {
        return new RoleSnapshot(code, name,
            authorities.stream().map(Authority::getSnapshot).collect(Collectors.toSet()));
    }

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
