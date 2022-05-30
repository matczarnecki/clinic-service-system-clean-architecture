package com.polsl.clinicservicesystem.dto.user;

import javax.validation.constraints.NotBlank;

public class EditUserRequest {

  @NotBlank
  private String username;

  private String password;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String email;

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

  public String getEmail() {
    return email;
  }

  void setEmail(String email) {
    this.email = email;
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
}
