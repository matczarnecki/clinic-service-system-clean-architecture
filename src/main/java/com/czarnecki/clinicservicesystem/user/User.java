package com.czarnecki.clinicservicesystem.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role", referencedColumnName = "code")
  Role role;

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
