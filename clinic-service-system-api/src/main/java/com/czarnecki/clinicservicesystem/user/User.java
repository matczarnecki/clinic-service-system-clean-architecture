package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private boolean isActive;
  private boolean isBlocked;
  private Integer numberOfFailedLogins;
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role", referencedColumnName = "code")
  private Role role;

  UserDto toDto() {
    return UserDto.builder()
        .withId(id)
        .withUsername(username)
        .withEmail(emailAddress)
        .withActive(isActive)
        .withRole(role.getCode())
        .withFirstName(firstName)
        .withLastName(lastName)
        .withBlocked(isBlocked)
        .build();
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
