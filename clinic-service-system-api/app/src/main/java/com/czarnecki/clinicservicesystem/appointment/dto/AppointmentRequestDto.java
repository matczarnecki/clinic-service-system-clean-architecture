package com.czarnecki.clinicservicesystem.appointment.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentRequestDto {

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime appointmentTime;

    @NotNull
    private Integer doctorId;

    @NotNull
    private Integer patientId;

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
