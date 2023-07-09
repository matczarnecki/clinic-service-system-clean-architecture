package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.patient.query.SimplePatientQueryDto;
import com.czarnecki.clinicservicesystem.user.query.SimpleUserQueryDto;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDateTime appointmentTime;
  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private SimpleUserQueryDto doctor;
  @ManyToOne
  @JoinColumn(name = "patient_id")
  private SimplePatientQueryDto patient;
  @Enumerated(EnumType.STRING)
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
