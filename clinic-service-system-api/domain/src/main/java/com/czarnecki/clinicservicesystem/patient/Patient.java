package com.czarnecki.clinicservicesystem.patient;

class Patient {
  private Integer id;
  private String firstName;
  private String lastName;


  Integer getId() {
    return id;
  }

  void setId(Integer id) {
    this.id = id;
  }

  String getFirstName() {
    return firstName;
  }

  void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  String getLastName() {
    return lastName;
  }

  void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
