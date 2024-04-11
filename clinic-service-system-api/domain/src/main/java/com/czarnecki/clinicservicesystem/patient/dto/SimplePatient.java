package com.czarnecki.clinicservicesystem.patient.dto;

public class SimplePatient {
    private Integer id;
    private String firstName;
    private String lastName;

    public SimplePatient(final Integer id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
