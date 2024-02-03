package com.czarnecki.clinicservicesystem.patient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
class SqlPatient {

    static SqlPatient fromPatient(Patient source) {
        var result = new SqlPatient();
        result.id = source.getId();
        result.firstName = source.getFirstName();
        result.lastName = source.getLastName();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    Patient toPatient() {
        var result = new Patient();
        result.setId(id);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        return result;
    }

}
