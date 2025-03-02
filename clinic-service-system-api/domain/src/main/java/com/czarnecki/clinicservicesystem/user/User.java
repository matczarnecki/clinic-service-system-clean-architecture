package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.Aggregate;
import com.czarnecki.clinicservicesystem.user.vo.UserSnapshot;

class User implements Aggregate<Integer, UserSnapshot> {

    static User from(final UserSnapshot userSnapshot) {
        return new User(userSnapshot.id(), userSnapshot.username(), userSnapshot.password(),
            userSnapshot.firstName(), userSnapshot.lastName(), userSnapshot.emailAddress(), userSnapshot.isActive(),
            userSnapshot.isBlocked(), userSnapshot.numberOfFailedLogins(), Role.from(userSnapshot.role()));
    }

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private boolean isActive;
    private boolean isBlocked;
    private Integer numberOfFailedLogins;
    private Role role;

    private User(Integer id, String username, String password, String firstName, String lastName, String emailAddress,
        boolean isActive, boolean isBlocked, Integer numberOfFailedLogins, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.numberOfFailedLogins = numberOfFailedLogins;
        this.role = role;
    }

    public User() {
    }

    @Override
    public UserSnapshot getSnapshot() {
        return new UserSnapshot(id, username, password, firstName, lastName, emailAddress,
            isActive, isBlocked, numberOfFailedLogins, role.getSnapshot());
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    void setBlocked(final boolean blocked) {
        isBlocked = blocked;
    }

    public Integer getNumberOfFailedLogins() {
        return numberOfFailedLogins;
    }

    void setNumberOfFailedLogins(final Integer numberOfFailedLogins) {
        this.numberOfFailedLogins = numberOfFailedLogins;
    }
}
