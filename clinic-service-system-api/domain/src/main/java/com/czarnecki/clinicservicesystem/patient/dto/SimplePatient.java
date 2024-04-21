package com.czarnecki.clinicservicesystem.patient.dto;

public class SimplePatient {

    public static SimplePatient from(final SimplePatientSnapshot snapshot) {
        return new SimplePatient(snapshot.id(), snapshot.firstName(), snapshot.lastName());
    }

    private final Integer id;
    private final String firstName;
    private final String lastName;

    private SimplePatient(final Integer id,
        final String firstName,
        final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SimplePatientSnapshot getSnapshot() {
        return new SimplePatientSnapshot(id, firstName, lastName);
    }
}
