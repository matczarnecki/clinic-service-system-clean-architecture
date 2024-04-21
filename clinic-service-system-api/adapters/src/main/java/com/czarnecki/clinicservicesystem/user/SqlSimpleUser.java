package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.SimpleUserSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class SqlSimpleUser {
    public static SqlSimpleUser fromSnapshot(SimpleUserSnapshot snapshot) {
        return new SqlSimpleUser(snapshot.id(), snapshot.firstName(), snapshot.lastName(), snapshot.emailAddress());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public SqlSimpleUser(Integer id, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public SqlSimpleUser() {
    }

    public SimpleUserSnapshot getSnapshot() {
        return new SimpleUserSnapshot(id, firstName, lastName, emailAddress);
    }
}
