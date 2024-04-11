package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;

import java.time.LocalDateTime;

class Appointment {
    private Integer id;
    private LocalDateTime appointmentTime;
    private SimpleUser doctor;
    private SimplePatient patient;
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

    SimpleUser getDoctor() {
        return doctor;
    }

    void setDoctor(SimpleUser doctor) {
        this.doctor = doctor;
    }

    SimplePatient getPatient() {
        return patient;
    }

    void setPatient(SimplePatient patient) {
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
