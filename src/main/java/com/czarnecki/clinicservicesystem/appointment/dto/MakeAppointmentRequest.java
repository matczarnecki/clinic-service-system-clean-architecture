package com.czarnecki.clinicservicesystem.appointment.dto;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class MakeAppointmentRequest {

  @NotBlank
  @Length(max = 255)
  private String diagnosis;

  @NotBlank
  @Length(max = 255)
  private String description;

  public String getDiagnosis() {
    return diagnosis;
  }

  void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public String getDescription() {
    return description;
  }

  void setDescription(String description) {
    this.description = description;
  }
}
