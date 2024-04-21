package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.vo.UserSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
class SqlUser {
    static SqlUser fromSnapshot(UserSnapshot snapshot) {
        return new SqlUser(snapshot.id(), snapshot.username(), snapshot.password(), snapshot.firstName(),
            snapshot.lastName(), snapshot.emailAddress(), snapshot.isActive(), snapshot.isBlocked(),
            snapshot.numberOfFailedLogins(), SqlRole.fromSnapshot(snapshot.role()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private boolean isActive;
    private boolean isBlocked;
    private Integer numberOfFailedLogins;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", referencedColumnName = "code")
    private SqlRole role;

    public SqlUser(Integer id, String username, String password, String firstName, String lastName, String emailAddress,
        boolean isActive, boolean isBlocked, Integer numberOfFailedLogins, SqlRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.numberOfFailedLogins = numberOfFailedLogins;
        this.role = role;
    }

    public SqlUser() {
    }

    UserSnapshot toSnapshot() {
        return new UserSnapshot(id, username, password, firstName, lastName, emailAddress, isActive, isBlocked,
            numberOfFailedLogins,
            role.toSnapshot());
    }

}

