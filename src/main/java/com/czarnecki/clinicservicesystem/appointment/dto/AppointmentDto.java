package com.czarnecki.clinicservicesystem.appointment.dto;

import com.czarnecki.clinicservicesystem.appointment.AppointmentStatus;
import com.czarnecki.clinicservicesystem.patient.PatientDto;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@JsonDeserialize(builder = AppointmentDto.Builder.class)
public class AppointmentDto {
    public static Builder builder() {
        return new Builder();
    }

    private AppointmentDto(Builder builder) {
        this.id = builder.id;
        this.appointmentTime = builder.appointmentTime;
        this.doctor = builder.doctor;
        this.patient = builder.patient;
        this.status = builder.status;
        this.diagnosis = builder.diagnosis;
        this.description = builder.description;
    }

    private final Integer id;
    private final LocalDateTime appointmentTime;
    private final UserDto doctor;
    private final PatientDto patient;
    private final AppointmentStatus status;
    private final String diagnosis;
    private final String description;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public UserDto getDoctor() {
        return doctor;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDescription() {
        return description;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private Integer id;
        private LocalDateTime appointmentTime;
        private UserDto doctor;
        private PatientDto patient;
        private AppointmentStatus status;
        private String diagnosis;
        private String description;

        private Builder() {

        }

        public AppointmentDto build() {
            return new AppointmentDto(this);
        }

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder withAppointmentTime(final LocalDateTime appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }

        public Builder withDoctor(final UserDto doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder withPatient(final PatientDto patient) {
            this.patient = patient;
            return this;
        }

        public Builder withStatus(final AppointmentStatus status) {
            this.status = status;
            return this;
        }

        public Builder withDiagnosis(final String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }
    }
}
