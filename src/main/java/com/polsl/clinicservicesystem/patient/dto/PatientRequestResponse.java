package com.polsl.clinicservicesystem.patient.dto;

import com.polsl.clinicservicesystem.patient.PatientEntity;
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

  public static PatientRequestResponse fromEntity(PatientEntity entity) {
    PatientRequestResponse dto = new PatientRequestResponse();
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setId(entity.getId());
    return dto;
  }
}
