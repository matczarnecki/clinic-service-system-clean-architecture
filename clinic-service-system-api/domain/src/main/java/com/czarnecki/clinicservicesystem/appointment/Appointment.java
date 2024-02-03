package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.user.query.SimpleUserQueryDto;
import com.czarnecki.clinicservicesystem.patient.query.SimplePatientQueryDto;
import java.time.LocalDateTime;

class Appointment {
  private Integer id;
  private LocalDateTime appointmentTime;
  private SimpleUserQueryDto doctor;
  private SimplePatientQueryDto patient;
  private AppointmentStatus status;
  private String diagnosis;
  private String description;


  Integer getId() {
    return id;
  }

  void setId(Integer id) {
    this.id = id;
  }

  LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  void setAppointmentTime(LocalDateTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  SimpleUserQueryDto getDoctor() {
    return doctor;
  }

  void setDoctor(SimpleUserQueryDto doctor) {
    this.doctor = doctor;
  }

  SimplePatientQueryDto getPatient() {
    return patient;
  }

  void setPatient(SimplePatientQueryDto patient) {
    this.patient = patient;
  }

  AppointmentStatus getStatus() {
    return status;
  }

  void setStatus(AppointmentStatus status) {
    this.status = status;
  }

  String getDiagnosis() {
    return diagnosis;
  }

  void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  String getDescription() {
    return description;
  }

  void setDescription(String description) {
    this.description = description;
  }
}
