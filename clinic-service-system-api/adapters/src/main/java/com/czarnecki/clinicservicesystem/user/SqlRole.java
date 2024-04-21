package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.vo.RoleSnapshot;
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

    static SqlRole fromSnapshot(final RoleSnapshot snapshot) {
        return new SqlRole(snapshot.code(), snapshot.name(), snapshot.authorities()
            .stream()
            .map(SqlAuthority::fromSnapshot)
            .collect(Collectors.toSet()));
    }

    @Id
    private String code;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authorities",
        joinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "authority_code", referencedColumnName = "code", nullable = false))
    private Set<SqlAuthority> authorities;

    public SqlRole(String code, String name, Set<SqlAuthority> authorities) {
        this.code = code;
        this.name = name;
        this.authorities = authorities;
    }

    public SqlRole() {
    }

    RoleSnapshot toSnapshot() {
        return new RoleSnapshot(code, name, authorities.stream()
            .map(SqlAuthority::toSnapshot).collect(Collectors.toSet()));
    }

}
