package com.czarnecki.clinicservicesystem.user.dto;


public class SimpleUser {
    public static SimpleUser from(final SimpleUserSnapshot snapshot) {
        return new SimpleUser(
            snapshot.id(),
            snapshot.firstName(),
            snapshot.lastName(),
            snapshot.emailAddress());
    }

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;

    public SimpleUser(final Integer id,
        final String firstName,
        final String lastName,
        final String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public SimpleUserSnapshot getSnapshot() {
        return new SimpleUserSnapshot(id, firstName, lastName, emailAddress);
    }
}
