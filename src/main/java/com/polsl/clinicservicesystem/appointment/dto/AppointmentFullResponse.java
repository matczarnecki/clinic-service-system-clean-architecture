package com.polsl.clinicservicesystem.appointment.dto;

import com.polsl.clinicservicesystem.patient.dto.PatientRequestResponse;
import com.polsl.clinicservicesystem.user.dto.UserResponse;
import com.polsl.clinicservicesystem.appointment.AppointmentEntity;

public class AppointmentFullResponse extends AppointmentResponse {
  private String diagnosis;
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

  public static AppointmentFullResponse fromEntity(AppointmentEntity entity) {
    AppointmentFullResponse dto = new AppointmentFullResponse();
    dto.setId(entity.getId());
    dto.setAppointmentTime(entity.getAppointmentTime());
    dto.setDoctor(UserResponse.fromEntity(entity.getDoctor()));
    dto.setPatient(PatientRequestResponse.fromEntity(entity.getPatient()));
    dto.setStatus(entity.getStatus());
    dto.setDiagnosis(entity.getDiagnosis());
    dto.setDescription(entity.getDescription());
    return dto;
  }
}
