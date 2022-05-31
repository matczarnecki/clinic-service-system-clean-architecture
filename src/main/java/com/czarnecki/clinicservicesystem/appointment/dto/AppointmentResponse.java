package com.czarnecki.clinicservicesystem.appointment.dto;

import com.czarnecki.clinicservicesystem.patient.dto.PatientRequestResponse;
import com.czarnecki.clinicservicesystem.user.dto.UserResponse;
import com.czarnecki.clinicservicesystem.appointment.Appointment;
import com.czarnecki.clinicservicesystem.appointment.AppointmentStatus;
import java.time.LocalDateTime;

public class AppointmentResponse {
  private Integer id;
  private LocalDateTime appointmentTime;
  private UserResponse doctor;
  private PatientRequestResponse patient;
  private AppointmentStatus status;

  public Integer getId() {
    return id;
  }

  void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  void setAppointmentTime(LocalDateTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public UserResponse getDoctor() {
    return doctor;
  }

  void setDoctor(UserResponse doctor) {
    this.doctor = doctor;
  }

  public PatientRequestResponse getPatient() {
    return patient;
  }

  void setPatient(PatientRequestResponse patient) {
    this.patient = patient;
  }

  public AppointmentStatus getStatus() {
    return status;
  }

  void setStatus(AppointmentStatus status) {
    this.status = status;
  }

  public static AppointmentResponse fromEntity(Appointment entity) {
    AppointmentResponse dto = new AppointmentResponse();
    dto.setId(entity.getId());
    dto.setAppointmentTime(entity.getAppointmentTime());
    dto.setDoctor(UserResponse.fromEntity(entity.getDoctor()));
    dto.setPatient(PatientRequestResponse.fromEntity(entity.getPatient()));
    dto.setStatus(entity.getStatus());
    return dto;
  }
}
