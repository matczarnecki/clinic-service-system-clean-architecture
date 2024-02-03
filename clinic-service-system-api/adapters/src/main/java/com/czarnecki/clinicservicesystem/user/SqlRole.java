package com.czarnecki.clinicservicesystem.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
class SqlRole {

    static SqlRole fromRole(Role source) {
        var result = new SqlRole();
        result.code = source.getCode();
        result.name = source.getName();
        result.authorities = source.getAuthorities()
                .stream()
                .map(SqlAuthority::fromAuthority)
                .collect(Collectors.toSet());
        return result;
    }

    @Id
    private String code;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authorities",
            joinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "authority_code", referencedColumnName = "code", nullable = false))
    private Set<SqlAuthority> authorities;

    Role toRole() {
        var result = new Role();
        result.setCode(code);
        result.setName(name);
        result.setAuthorities(authorities.stream()
                .map(SqlAuthority::toAuthority)
                .collect(Collectors.toSet()));
        return result;
    }

}
