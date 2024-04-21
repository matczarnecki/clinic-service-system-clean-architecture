package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.patient.dto.SimplePatientSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class SqlSimplePatient {

    public static SqlSimplePatient fromSnapshot(final SimplePatientSnapshot snapshot) {
        return new SqlSimplePatient(snapshot.id(), snapshot.firstName(), snapshot.lastName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public SqlSimplePatient(final Integer id,
        final String firstName,
        final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SqlSimplePatient() {
    }

    public SimplePatientSnapshot getSnapshot() {
        return new SimplePatientSnapshot(id, firstName, lastName);
    }

}
