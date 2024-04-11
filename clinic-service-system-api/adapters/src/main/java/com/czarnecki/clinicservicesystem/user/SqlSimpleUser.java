package com.czarnecki.clinicservicesystem.user;


import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class SqlSimpleUser {
    public static SqlSimpleUser fromSimpleUser(SimpleUser source) {
        var result = new SqlSimpleUser();
        result.id = source.getId();
        result.firstName = source.getFirstName();
        result.lastName = source.getLastName();
        result.emailAddress = source.getEmailAddress();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public SimpleUser toSimpleUser() {
        return new SimpleUser(id, firstName, lastName, emailAddress);
    }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
