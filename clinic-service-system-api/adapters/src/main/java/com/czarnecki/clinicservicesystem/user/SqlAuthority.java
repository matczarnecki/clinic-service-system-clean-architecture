package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.vo.AuthoritySnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
class SqlAuthority {

    static SqlAuthority fromSnapshot(final AuthoritySnapshot snapshot) {
        return new SqlAuthority(snapshot.code(), snapshot.name(), snapshot.description());
    }

    @Id
    private String code;

    private String name;

    private String description;

    public SqlAuthority(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public SqlAuthority() {
    }

    AuthoritySnapshot toSnapshot() {
        return new AuthoritySnapshot(code, name, description);
    }

}
