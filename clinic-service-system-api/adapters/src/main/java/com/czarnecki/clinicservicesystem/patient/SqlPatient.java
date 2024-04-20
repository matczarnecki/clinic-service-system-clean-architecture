package com.czarnecki.clinicservicesystem.patient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
class SqlPatient {

    static SqlPatient fromSnapshot(final PatientSnapshot snapshot) {
        return new SqlPatient(snapshot.id(), snapshot.firstName(), snapshot.lastName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public SqlPatient(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SqlPatient() {
    }

    PatientSnapshot toSnapshot() {
        return new PatientSnapshot(id, firstName, lastName);
    }

}
