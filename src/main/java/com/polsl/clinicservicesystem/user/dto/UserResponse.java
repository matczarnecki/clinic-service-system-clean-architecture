package com.polsl.clinicservicesystem.user.dto;

import com.polsl.clinicservicesystem.user.User;

public class UserResponse {

  private Integer id;

  private String username;

  private String email;

  private boolean isActive;

  private String role;

  private String firstName;

  private String lastName;

  private boolean isBlocked;

  public static UserResponse fromEntity(User entity) {
    UserResponse response = new UserResponse();
    response.setId(entity.getId());
    response.setUsername(entity.getUsername());
    response.setEmail(entity.getEmailAddress());
    response.setActive(entity.isActive());
    response.setRole(entity.getRole().getCode());
    response.setFirstName(entity.getFirstName());
    response.setLastName(entity.getLastName());
    response.setBlocked(entity.isBlocked());
    return response;
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

  public String getEmail() {
    return email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  public boolean isActive() {
    return isActive;
  }

  void setActive(boolean active) {
    isActive = active;
  }

  public String getRole() {
    return role;
  }

  void setRole(String role) {
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
}
