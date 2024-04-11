package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class SqlSimplePatient {

    public static SqlSimplePatient fromSimplePatient(SimplePatient simplePatient) {
        var result = new SqlSimplePatient();
        result.id = simplePatient.getId();
        result.firstName = simplePatient.getFirstName();
        result.lastName = simplePatient.getLastName();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public SqlSimplePatient() {
    }

    public SqlSimplePatient(final Integer id,
        final String firstName,
        final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SimplePatient toSimplePatient() {
        return new SimplePatient(id, firstName, lastName);
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

}
