package com.czarnecki.clinicservicesystem.patient.dto;

import com.czarnecki.clinicservicesystem.patient.Patient;
import javax.validation.constraints.NotBlank;

public class PatientRequestResponse {

  private Integer id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  public Integer getId() {
    return id;
  }

  void setId(Integer id) {
    this.id = id;
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

  public static PatientRequestResponse fromEntity(Patient entity) {
    PatientRequestResponse dto = new PatientRequestResponse();
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setId(entity.getId());
    return dto;
  }
}
