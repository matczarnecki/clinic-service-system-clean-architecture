package com.czarnecki.clinicservicesystem.appointment;


import com.czarnecki.clinicservicesystem.patient.Patient;
import com.czarnecki.clinicservicesystem.user.User;

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
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDateTime appointmentTime;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private User doctor;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus status;

  private String diagnosis;

  private String description;

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

  public User getDoctor() {
    return doctor;
  }

  void setDoctor(User doctor) {
    this.doctor = doctor;
  }

  public Patient getPatient() {
    return patient;
  }

  void setPatient(Patient patient) {
    this.patient = patient;
  }

  public AppointmentStatus getStatus() {
    return status;
  }

  void setStatus(AppointmentStatus status) {
    this.status = status;
  }

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
