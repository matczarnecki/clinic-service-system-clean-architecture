package com.czarnecki.clinicservicesystem.user;


import com.czarnecki.clinicservicesystem.DomainEntity;
import com.czarnecki.clinicservicesystem.user.vo.AuthoritySnapshot;

public class Authority implements DomainEntity<String, AuthoritySnapshot> {

    static Authority from(final AuthoritySnapshot snapshot) {
        return new Authority(snapshot.code(), snapshot.name(), snapshot.description());
    }

    private String code;

    private String name;

    private String description;

    private Authority(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @Override
    public AuthoritySnapshot getSnapshot() {
        return new AuthoritySnapshot(code, name, description);
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

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
