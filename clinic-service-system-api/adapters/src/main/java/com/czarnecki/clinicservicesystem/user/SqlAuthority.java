package com.czarnecki.clinicservicesystem.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
class SqlAuthority {

    static SqlAuthority fromAuthority(Authority source) {
        var result = new SqlAuthority();
        result.code = source.getCode();
        result.name = source.getName();
        result.description = source.getDescription();
        return result;
    }

    @Id
    private String code;

    private String name;

    private String description;

    Authority toAuthority() {
        var result = new Authority();
        result.setCode(code);
        result.setName(name);
        result.setDescription(description);
        return result;
    }

}
