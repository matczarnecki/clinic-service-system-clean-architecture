package com.czarnecki.clinicservicesystem.user;

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
public class SqlUser {
    static SqlUser fromUser(User source) {
        var result = new SqlUser();
        result.id = source.getId();
        result.username = source.getUsername();
        result.password = source.getPassword();
        result.firstName = source.getFirstName();
        result.lastName = source.getLastName();
        result.emailAddress = source.getEmailAddress();
        result.isActive = source.isActive();
        result.isBlocked = source.isBlocked();
        result.numberOfFailedLogins = source.getNumberOfFailedLogins();
        return result;
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
    private Role role;

    User toUser() {
        var result = new User();
        result.setId(id);
        result.setUsername(username);
        result.setPassword(password);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setEmailAddress(emailAddress);
        result.setActive(isActive);
        result.setBlocked(isBlocked);
        result.setNumberOfFailedLogins(numberOfFailedLogins);
        return result;
    }

}

